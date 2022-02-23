package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create

import android.app.TimePickerDialog
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCreateSubjectBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.utility.TimeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SubjectCreateActivity : BaseActivity<ActivityCreateSubjectBinding, CreateSubjectViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_create_subject
    override val viewModel: CreateSubjectViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setViewModel() // 뷰모델 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setViewModel() {
        binding.viewModel = this@SubjectCreateActivity.viewModel
    }

    // 뷰 초기화
    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_black_close_btn) // 뒤로가기 버튼 변경
            setOnClickListener() // 클릭 리스너 설정

            // 과외 시간대 텍스트뷰 설정
            setClassTime()

            amPmRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.am_radio_btn -> { // 오전 버튼 클릭시
                        // 설정된 시간이 오후면 -12 연산해서 저장
                        if (TimeConverter().isPM(viewModel!!.hour)) {
                            viewModel!!.hour -= 12
                        }
                    }
                    R.id.pm_radio_btn -> { // 오후 버튼 클릭시
                        // 설정된 시간이 오전이면 +12 연산해서 저장
                        if (TimeConverter().isPM(viewModel!!.hour).not()) {
                            viewModel!!.hour += 12
                        }
                    }
                }

                notifyTimeChanged() // 변경된 시간대로 텍스트뷰 설정
            }

            // ClassName 값이 변경될 때마다 '완료' 버튼 클릭 가능 여부를 변경한다.
            viewModel.className.observe(this@SubjectCreateActivity) {
                viewModel.setDoneClickable()
            }

            // '완료' 클릭 가능 여부 관찰
            viewModel.isDoneClickable.observe(this@SubjectCreateActivity) {
                doneBtn.isEnabled = true // 완료버튼 활성화
            }

            // '수업 생성 성공 여부' 관찰
            viewModel.isMakeSuccess.observe(this@SubjectCreateActivity) { isSuccess ->
                if (isSuccess) { // 수업 생성 성공
                    // setResult()로 초대코드 Dialog를 보여주기 위한 결과 반환
                    finish() //액티비티 종료
                } else { // 수업 생성 실패
                    showToast(getString(R.string.failed_make_class)) // 실패 Toast 출력
                }
            }
        }
    }

    private fun setOnClickListener() {
        with(binding) {
            timeSelectContainer.setOnClickListener(this@SubjectCreateActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.time_select_container -> {
                // 과외 시간대를 설정할 수 있는 Time Picker Dialog 보여줌
                val timePickerDialog = TimePickerDialog(
                    this,
                    { view, hourOfDay, minute ->
                        // ViewModel 시간 설정
                        viewModel.hour = hourOfDay
                        viewModel.minute = minute

                        setClassTime() // 과외 시간 텍스트뷰 설정
                    }, viewModel.hour, viewModel.minute, true
                )

                timePickerDialog.show()
            }
        }
    }

    // 선택한 시간대로 과외 시간 텍스트뷰 갱신
    private fun setClassTime() {
        // 수업 시간 (오전, 오후) 라디오 버튼 설정
        // 선택한 시간대가 오후라면
        if (TimeConverter().isPM(viewModel.hour)) {
            binding.amPmRadioGroup.check(R.id.pm_radio_btn)
        } else {
            binding.amPmRadioGroup.check(R.id.am_radio_btn)
        }

        notifyTimeChanged() // 변경된 시간대로 텍스트뷰 설정
    }

    private fun notifyTimeChanged() {
        binding.hourTextView.text =
            getString(
                R.string.hour_or_minute_format,
                TimeConverter().convertHourPmIncludedZero(viewModel.hour)
            )
        binding.minTextView.text =
            getString(
                R.string.hour_or_minute_format,
                TimeConverter().convertHourPmIncludedZero(viewModel.minute)
            )
    }
}