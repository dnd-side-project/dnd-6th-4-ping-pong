package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.request

import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityEditRequestBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.edit.StudentScheduleEditActivity
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import com.dnd.sixth.lmsservice.presentation.utility.TimeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class EditRequestActivity : BaseActivity<ActivityEditRequestBinding, EditRequestViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_edit_request
    override val viewModel: EditRequestViewModel by viewModel()

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
            setDateTimePicker(this) // DateTime Picker 설정
            setListener(this) // 리스너 설정

            // 툴바 설정
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정
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
                                this@EditRequestActivity,
                                R.anim.anim_drop_down
                            )
                        scrollDateContainer.visibility = View.VISIBLE
                        scrollDateContainer.startAnimation(dropDownAnimation)
                        dateTimeTextView.setTextColor(
                            ContextCompat.getColor(
                                this@EditRequestActivity,
                                R.color.grayC4
                            )
                        )
                    }
                    // 보이는 상태에서는 드롭 업 애니메이션과 함께 Gone
                    else {
                        val dropUpAnimation =
                            AnimationUtils.loadAnimation(
                                this@EditRequestActivity,
                                R.anim.anim_drop_up
                            ).apply {
                                setAnimationListener(object : Animation.AnimationListener {
                                    override fun onAnimationStart(animation: Animation?) {}
                                    override fun onAnimationEnd(animation: Animation?) {
                                        // 애니메이션 종료시 Visibility Gone 으로 설정
                                        scrollDateContainer.visibility = View.GONE
                                        dateTimeTextView.setTextColor(
                                            ContextCompat.getColor(
                                                this@EditRequestActivity,
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
            R.id.done_btn -> {
                setResult(StudentScheduleEditActivity.INTENT_EDIT_REQUEST_ACTIVITY_CODE)
                finish()
            }
        }
    }

    private fun setListener(binding: ActivityEditRequestBinding) {
        with(binding) {
            dateContainer.setOnClickListener(this@EditRequestActivity)
            doneBtn.setOnClickListener(this@EditRequestActivity)
        }
    }

    private fun setDateTimePicker(binding: ActivityEditRequestBinding) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

    private fun loadDateOnResume() {
        viewModel.pickedDate?.let { setDateTimeText(it) } // 과외 일정, 시간 텍스트
    }

}