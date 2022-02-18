package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityScheduleAddBinding
import com.dnd.sixth.lmsservice.databinding.DialogPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.PushTimePickerActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import com.dnd.sixth.lmsservice.presentation.utility.CustomInputFilter
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import com.dnd.sixth.lmsservice.presentation.utility.TimeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ScheduleAddActivity : BaseActivity<ActivityScheduleAddBinding, ScheduleAddViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_schedule_add
    override val viewModel: ScheduleAddViewModel by viewModel()
    private var pushTimeResultLauncher: ActivityResultLauncher<Intent>? = null // 푸시 타임 피커 액티비티 런쳐

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            setDateTimePicker() // DateTime Picker 설정
            setListener() // 리스너 설정
            setActivityLauncher() // 액티비티 런처 설정

            // 숫자만 입력 받을 수 있도록 Filter 설정
            classRoundEditText.filters = arrayOf(CustomInputFilter())

            // 수업 회차 입력값이 변경됨을 감지
            viewModel?.classRound?.observe(this@ScheduleAddActivity) {
                classRoundEditText.setSelection(classRoundEditText.length()) // 에딧 텍스트 커서 맨 뒤에 배치
                if(it < 1) {
                    viewModel?.setDefaultClassRound()
                }
            }

            DialogPushTimePickerBinding.inflate(layoutInflater)


            // 푸시타임 데이터가 변경되면 텍스트 및 색상을 변경한다.
            viewModel?.pushTime?.observe(this@ScheduleAddActivity) { pushTime ->
                if (pushTime == null) {
                    return@observe
                }
                notiTextView.isEnabled = true // 색상 변경을 위해 활성화 True
                when (pushTime) {
                    PushTime.NONE -> {
                        notiTextView.text = "없음"
                    }
                    PushTime.TEN -> {
                        notiTextView.text = "10분 전"
                    }
                    PushTime.THIRTY -> {
                        notiTextView.text = "30분 전"
                    }
                    PushTime.ONE_HOUR -> {
                        notiTextView.text = "1시간 전"
                    }
                    PushTime.THREE_HOUR -> {
                        notiTextView.text = "3시간 전"
                    }

                }
            }
        }
    }

    private fun setActivityLauncher() {
        pushTimeResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == INTENT_PUSH_ACTIVITY_CODE) {
                    val data = result.data // 푸시 타임 데이터
                    val newPushTime: PushTime? =
                        data?.getSerializableExtra(INTENT_PUSH_TOKEN_KEY) as PushTime?
                    viewModel.changePushTime(newPushTime) // 새로 받아온 푸시 타임으로 갱신
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.date_container -> {
                // 날짜 피커뷰를 상태에 따라 보여주거나 가린다.
                showOrHideDateScrollView()
            }
            R.id.class_round_container -> {
                // 수업 회차 피커뷰를 상태에 따라 보여주거나 가린다.
                showOrHideRoundView()
            }
            R.id.class_noti_container, R.id.noti_down_arrow_btn -> { // 푸시 타임 선택할 수 있는 액티비티로 이동
                // 현재 푸시 타임 데이터 전달
                val intent = Intent(this, PushTimePickerActivity::class.java).putExtra(
                    INTENT_PUSH_TOKEN_KEY, viewModel.pushTime.value
                )
                pushTimeResultLauncher?.launch(intent) // 런처를 통해 푸시 선택 액티비티 선택
            }
        }
    }

    private fun setListener() {
        with(binding) {
            dateContainer.setOnClickListener(this@ScheduleAddActivity)
            classRoundCountView.setOnClickListener(this@ScheduleAddActivity)
            classRoundContainer.setOnClickListener(this@ScheduleAddActivity)
            countMinusBtn.setOnClickListener(this@ScheduleAddActivity)
            countPlusBtn.setOnClickListener(this@ScheduleAddActivity)
            classNotiContainer.setOnClickListener(this@ScheduleAddActivity)
            notiDownArrowBtn.setOnClickListener(this@ScheduleAddActivity)

        }
    }

    private fun setDateTimePicker() {
        binding.dateTimePicker.setOnSelectedDateChangedListener { date ->
            // 화면 회전시 초기화되는 것을 방지하기 위해
            // ViewModel에 데이터 저장
            viewModel.pickedDate = date
            setDateTimeText(date)
        }
    }

    // 날짜를 선택할 수 있는 PickerView를 보여주거나 가린다.
    private fun showOrHideDateScrollView() {
        // Expandable 처리
        // 가려져 있는 상태에서는 드롭 다운 애니메이션과 함께 Visible
        with(binding) {
            if (scrollDateContainer.visibility == View.GONE) {
                val dropDownAnimation =
                    AnimationUtils.loadAnimation(
                        this@ScheduleAddActivity,
                        R.anim.anim_drop_down
                    )
                scrollDateContainer.visibility = View.VISIBLE
                scrollDateContainer.startAnimation(dropDownAnimation)
                dateTimeTextView.setTextColor(
                    ContextCompat.getColor(
                        this@ScheduleAddActivity,
                        R.color.grayC4
                    )
                )
            }
            // 보이는 상태에서는 드롭 업 애니메이션과 함께 Gone
            else {
                val dropUpAnimation =
                    AnimationUtils.loadAnimation(
                        this@ScheduleAddActivity,
                        R.anim.anim_drop_up
                    ).apply {
                        setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {
                                // 애니메이션 종료시 Visibility Gone 으로 설정
                                scrollDateContainer.visibility = View.GONE
                                dateTimeTextView.setTextColor(
                                    ContextCompat.getColor(
                                        this@ScheduleAddActivity,
                                        R.color.richBlack
                                    )
                                )
                            }

                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }

                scrollDateContainer.startAnimation(dropUpAnimation)
            }
        }


    }


    // 회차를 선택할 수 있는 View를 보여주거나 가린다.
    private fun showOrHideRoundView() {
        // Expandable 처리
        // 가려져 있는 상태에서는 드롭 다운 애니메이션과 함께 Visible
        if (binding.classRoundCountView.visibility == View.GONE) {
            val dropDownAnimation =
                AnimationUtils.loadAnimation(this, R.anim.anim_drop_down)
            binding.classRoundCountView.visibility = View.VISIBLE
            binding.classRoundCountView.startAnimation(dropDownAnimation)
        }
        // 보이는 상태에서는 드롭 업 애니메이션과 함께 Gone
        else {
            val dropUpAnimation =
                AnimationUtils.loadAnimation(this, R.anim.anim_drop_up).apply {
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {
                            // 애니메이션 종료시 Visibility Gone 으로 설정
                            binding.classRoundCountView.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                }
            binding.classRoundCountView.startAnimation(dropUpAnimation)
        }
    }


    // DateTimePicker 변경시 해당 메서드로 Calendar 객체를 전달하여
    // 화면 갱신
    private fun setDateTimeText(date: Calendar) {
        val year = date[Calendar.YEAR]
        val month = date[Calendar.MONTH]
        val dayOfMonth = date[Calendar.DAY_OF_MONTH]
        val dayOfWeek = date[Calendar.DAY_OF_WEEK]
        val hour = date[Calendar.HOUR_OF_DAY]
        val minute = date[Calendar.MINUTE]

        /*
        * 날짜 형식
        * ex) 2022. 01. 22. 토   오후 03 : 30
        * */
        val dateString: String = String.format(
            Locale.KOREA,
            getString(R.string.class_date_time_dot_format),
            year,
            month + 1,
            dayOfMonth,
            DateConverter().getDayOfWeek(dayOfWeek), // 숫자 요일을 한국어로 변환
            TimeConverter().getAMPM(date), // 오전 오후를 구분하여 반환해주는 함수
            TimeConverter().convertHourInPM(hour), // 14시 -> 2시로 변환
            minute
        )


        with(binding) {
            dateTimeTextView.text = dateString
        }
    }

    override fun onResume() {
        super.onResume()

        // onResume 호출시 ViewModel에 저장된 데이터를 View에 적용
        loadDateOnResume()
    }

    private fun loadDateOnResume() {
        viewModel.pickedDate?.let { setDateTimeText(it) } // 과외 일정, 시간 텍스트
    }

    private fun hideKeyBoard() {
        val focusedView = currentFocus
        focusedView?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusedView.windowToken, 0);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }


    companion object {
        const val INTENT_PUSH_TOKEN_KEY = "newPushToken"
        const val INTENT_PUSH_ACTIVITY_CODE = 1000
    }


}