package com.dnd.sixth.lmsservice.presentation.home.classes.calendar.add

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.core.text.isDigitsOnly
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityClassAddBinding
import com.dnd.sixth.lmsservice.databinding.DialogPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import com.dnd.sixth.lmsservice.presentation.utility.TimeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ClassAddActivity : BaseActivity<ActivityClassAddBinding, ClassAddViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_class_add
    override val viewModel: ClassAddViewModel by viewModel()

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

            setDateTimePicker(this) // DateTime Picker 설정
            setListener(this) // 리스너 설정

            // 수업 회차 EditText TextWatcher
            val classRoundTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    // EditText의 값을 아예 지워서 비어지게 되는 경우에는
                    if (classRoundEditText.text.isBlank()) {
                        // 기본 값인 1로 설정한다.
                        viewModel?.setDefaultClassRound()
                    } else {
                        // 그렇지 않고, 어떠한 값이라도 입력이 된다면 classRound 라이브 데이터 갱신
                        if (classRoundEditText.text.isDigitsOnly()) {
                            viewModel?.setClassRound(classRoundEditText.text.toString().toInt())
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            }

            // ClassRound의 TextWatcher 등록
            classRoundEditText.addTextChangedListener(classRoundTextWatcher)


            // 수업 회차 입력값이 변경됨을 감지
            viewModel!!.classRound.observe(this@ClassAddActivity) {
                // 무한 루프를 방지하기 위해 TextWatcher 일시적으로 제거
                classRoundEditText.removeTextChangedListener(classRoundTextWatcher)

                classRoundEditText.setText(viewModel!!.classRound.value?.toString()) // 에딧 텍스트 값 변경
                classRoundTextView.text = getString(R.string.class_round_format, it) // 텍스트 값 변경
                classRoundEditText.setSelection(classRoundEditText.length()) // 에딧 텍스트 커서 맨 뒤에 배치

                // 로직 수행 후에 TextWatcher 다시 추가
                classRoundEditText.addTextChangedListener(classRoundTextWatcher)
            }

            DialogPushTimePickerBinding.inflate(layoutInflater)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.date_container -> {
                with(binding) {
                    // Expandable 처리
                    // 가려져 있는 상태에서는 드롭 다운 애니메이션과 함께 Visible
                    if (scrollDateContainer.visibility == View.GONE) {
                        val dropDownAnimation =
                            AnimationUtils.loadAnimation(
                                this@ClassAddActivity,
                                R.anim.anim_drop_down
                            )
                        scrollDateContainer.visibility = View.VISIBLE
                        scrollDateContainer.startAnimation(dropDownAnimation)
                        dateTextView.setTextColor(Color.parseColor("#C4C4C4"))
                        timeTextView.setTextColor(Color.parseColor("#C4C4C4"))
                    }
                    // 보이는 상태에서는 드롭 업 애니메이션과 함께 Gone
                    else {
                        val dropUpAnimation =
                            AnimationUtils.loadAnimation(
                                this@ClassAddActivity,
                                R.anim.anim_drop_up
                            ).apply {
                                setAnimationListener(object : Animation.AnimationListener {
                                    override fun onAnimationStart(animation: Animation?) {}
                                    override fun onAnimationEnd(animation: Animation?) {
                                        // 애니메이션 종료시 Visibility Gone 으로 설정
                                        scrollDateContainer.visibility = View.GONE
                                        dateTextView.setTextColor(Color.parseColor("#000000"))
                                        timeTextView.setTextColor(Color.parseColor("#000000"))
                                    }

                                    override fun onAnimationRepeat(animation: Animation?) {}
                                })
                            }

                        scrollDateContainer.startAnimation(dropUpAnimation)
                    }
                }
            }
            R.id.class_round_container -> {
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
            R.id.count_plus_btn -> {
                // 수업 회차 + 버튼 클릭시 1을 더해준다.
                viewModel.plusClassRound()
                //binding.classRoundEditText.clearFocus() // 포커스 해제
            }
            R.id.count_minus_btn -> {
                // 수업 회차 - 버튼 클릭시 1을 빼준다.
                viewModel.minusClassRound()
                //binding.classRoundEditText.clearFocus() // 포커스 해제
            }
        }
    }

    private fun setListener(binding: ActivityClassAddBinding) {
        with(binding) {
            dateContainer.setOnClickListener(this@ClassAddActivity)
            classRoundCountView.setOnClickListener(this@ClassAddActivity)
            classRoundContainer.setOnClickListener(this@ClassAddActivity)
            countMinusBtn.setOnClickListener(this@ClassAddActivity)
            countPlusBtn.setOnClickListener(this@ClassAddActivity)
        }
    }

    private fun setDateTimePicker(binding: ActivityClassAddBinding) {
        binding.dateTimePicker.setOnSelectedDateChangedListener { date ->
            // 화면 회전시 초기화되는 것을 방지하기 위해
            // ViewModel에 데이터 저장
            viewModel.pickedDate = date
            setDateTimeText(date)
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
        * ex) 2022년 1월 22일 (토)
        * */
        val dateString: String = String.format(
            Locale.KOREA,
            getString(R.string.class_date_format),
            year,
            month + 1,
            dayOfMonth,
            DateConverter().getDayOfWeek(dayOfWeek) // 숫자 요일을 한국어로 변환
        )

        /*
        * 시간 형식
        * ex) 오후 03 : 30
        * */
        val timeString: String = String.format(
            Locale.KOREA,
            getString(R.string.class_time_format),
            TimeConverter().getAMPM(date), // 오전 오후를 구분하여 반환해주는 함수
            TimeConverter().getHourInPM(hour), // 14시 -> 2시로 변환
            minute
        )

        with(binding) {
            dateTextView.text = dateString
            timeTextView.text = timeString
        }
    }

    override fun onResume() {
        super.onResume()
        // onResume 호출시 ViewModel에 저장된 데이터를 View에 적용
        loadDateOnResume()
    }

    private fun loadDateOnResume() {
        viewModel.pickedDate?.let { setDateTimeText(it) } // 과외 일정, 시간 텍스트
        binding.classRoundTextView.text =
            getString(R.string.class_round_format, viewModel.classRound.value) // 수업 회차 Text ex) 7회차
        binding.classRoundEditText.setText(viewModel.classRound.value.toString()) // 수업 회차 EditText
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


}