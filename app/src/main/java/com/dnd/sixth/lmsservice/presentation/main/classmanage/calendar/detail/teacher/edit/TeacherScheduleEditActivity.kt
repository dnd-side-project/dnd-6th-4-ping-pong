package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.teacher.edit

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityTeacherScheduleEditBinding
import com.dnd.sixth.lmsservice.databinding.DialogPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.PushTimePickerActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import com.dnd.sixth.lmsservice.presentation.utility.CustomInputFilter
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class TeacherScheduleEditActivity : BaseActivity<ActivityTeacherScheduleEditBinding, TeacherScheduleEditViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_teacher_schedule_edit
    override val viewModel: TeacherScheduleEditViewModel by viewModel()
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null // 푸시 타임 피커 액티비티 런쳐

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
        binding.dateConverter = DateConverter() // DateConverter 객체 바인딩
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
            viewModel?.classRound?.observe(this@TeacherScheduleEditActivity) {
                classRoundEditText.setSelection(classRoundEditText.length()) // 에딧 텍스트 커서 맨 뒤에 배치
                if(it < 1) {
                    viewModel?.setDefaultClassRound()
                }
            }

            DialogPushTimePickerBinding.inflate(layoutInflater)


            // 푸시타임 데이터가 변경되면 텍스트 및 색상을 변경한다.
            viewModel?.pushTime?.observe(this@TeacherScheduleEditActivity) { pushTime ->
                if (pushTime == null) {
                    return@observe
                }
                notiTextView.isEnabled = true // 색상 변경을 위해 활성화 True
                notiTextView.text = pushTime.timeText // 푸시 알림 텍스트뷰 갱신
            }
        }
    }

    private fun setActivityLauncher() {
        activityResultLauncher =
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
                activityResultLauncher?.launch(intent) // 런처를 통해 푸시 선택 액티비티 선택
            }
        }
    }

    private fun setListener() {
        with(binding) {
            dateContainer.setOnClickListener(this@TeacherScheduleEditActivity)
            classRoundCountView.setOnClickListener(this@TeacherScheduleEditActivity)
            classRoundContainer.setOnClickListener(this@TeacherScheduleEditActivity)
            countMinusBtn.setOnClickListener(this@TeacherScheduleEditActivity)
            countPlusBtn.setOnClickListener(this@TeacherScheduleEditActivity)
            classNotiContainer.setOnClickListener(this@TeacherScheduleEditActivity)
            notiDownArrowBtn.setOnClickListener(this@TeacherScheduleEditActivity)

        }
    }

    private fun setDateTimePicker() {
        binding.dateTimePicker.setOnSelectedDateChangedListener { dateCalendar ->
            /* 리스너를 통해 전달받은 Calendar 객체는 1일이 뺀 결과가 반환 됨 */
            val dateClone = dateCalendar.clone() as Calendar // 캘린더 객체 클론
            dateClone.add(Calendar.DAY_OF_MONTH, 1) // 1일 추가
            viewModel.pickedDate.value = dateClone.time // ViewModel에 전달
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
                        this@TeacherScheduleEditActivity,
                        R.anim.anim_drop_down
                    )
                scrollDateContainer.visibility = View.VISIBLE
                scrollDateContainer.startAnimation(dropDownAnimation)
                dateTimeTextView.setTextColor(
                    ContextCompat.getColor(
                        this@TeacherScheduleEditActivity,
                        R.color.grayC4
                    )
                )
            }
            // 보이는 상태에서는 드롭 업 애니메이션과 함께 Gone
            else {
                val dropUpAnimation =
                    AnimationUtils.loadAnimation(
                        this@TeacherScheduleEditActivity,
                        R.anim.anim_drop_up
                    ).apply {
                        setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {
                                // 애니메이션 종료시 Visibility Gone 으로 설정
                                scrollDateContainer.visibility = View.GONE
                                dateTimeTextView.setTextColor(
                                    ContextCompat.getColor(
                                        this@TeacherScheduleEditActivity,
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