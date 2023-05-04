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
        binding.dateConverter = DateConverter() // DateConverter 객체 바인딩
    }

    private fun initView() {
        with(binding) {
            // 툴바 설정
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정

            setDateTimePicker(this) // DateTime Picker 설정
            setListener(this) // 리스너 설정
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
        binding.dateTimePicker.setOnSelectedDateChangedListener { dateCalendar ->
            /* 리스너를 통해 전달받은 Calendar 객체는 1일이 뺀 결과가 반환 됨 */
            val dateClone = dateCalendar.clone() as Calendar // 캘린더 객체 클론
            dateClone.add(Calendar.DAY_OF_MONTH, 1) // 1일 추가
            viewModel.pickedDate.value = dateClone.time // ViewModel에 전달
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }


}