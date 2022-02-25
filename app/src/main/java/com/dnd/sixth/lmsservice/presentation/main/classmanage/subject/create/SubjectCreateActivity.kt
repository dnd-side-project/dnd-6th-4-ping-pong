package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create

import android.app.TimePickerDialog
import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.databinding.ActivityCreateSubjectBinding
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.extensions.convertDowBit
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarFragment
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.SubjectFragment
import com.dnd.sixth.lmsservice.presentation.utility.COLOR_COUNT
import com.dnd.sixth.lmsservice.presentation.utility.SUBJECT_COLOR
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

            amPmRadioGroup.setOnCheckedChangeListener { _, checkedId ->
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
            viewModel?.subjectName?.observe(this@SubjectCreateActivity) {
                viewModel?.setDoneClickable()
            }

            // '완료' 클릭 가능 여부 관찰
            viewModel?.isDoneClickable?.observe(this@SubjectCreateActivity) { isClickable ->
                doneBtn.isEnabled = isClickable // 완료버튼 활성화
            }

            doneBtn.setOnClickListener {
                val resultIntent = Intent().putExtra(
                    SubjectFragment.INTENT_CREATE_SUBJECT_ENTITY_KEY,
                    SubjectEntity(
                        2,
                        viewModel!!.subjectName.value!!,
                        viewModel!!.salaryDay.countInt,
                        "15 : 00 - 16 : 30",
                        0,
                        0,
                        DateColor.ORANGE.ordinal,
                        viewModel!!._weekOfDayList.value!!.convertDowBit()
                    )
                )

                setResult(SubjectFragment.INTENT_CREATE_SUBJECT_ACTIVITY_CODE, resultIntent) // 초대코드 Dialog를 보여주기 위한 결과 반환
                finish() //액티비티 종료
            }

            /* SubjectEntity를 정상적으로 서버에 저장시
            * 해당 Entity를 반환하면서 Activity를 종료합니다.
            *  */
            viewModel?.resultSubject?.observe(this@SubjectCreateActivity) { resultSubjectEntity ->
                if (resultSubjectEntity != null) { // 수업 생성 성공

                    // 생성한 수업의 SubjectEntity를 담는다.
                    // + 초대코드 Dialog를 보여주기 위한 결과 반환
                    val resultIntent = Intent().putExtra(
                        SubjectFragment.INTENT_CREATE_SUBJECT_ENTITY_KEY,
                        resultSubjectEntity
                    )

                    val preferenceManager = PreferenceManager(this@SubjectCreateActivity)
                    // 현재 색상의 Enum Ordinal
                    val currentColorOrdinal = preferenceManager.getInt(SUBJECT_COLOR)
                    // 1을 더해서 다음 색상을 준비합니다.
                    preferenceManager.setInt(SUBJECT_COLOR, (currentColorOrdinal + 1) % COLOR_COUNT)

                    setResult(SubjectFragment.INTENT_CREATE_SUBJECT_ACTIVITY_CODE, resultIntent) // 초대코드 Dialog를 보여주기 위한 결과 반환
                    finish() //액티비티 종료
                } else { // 수업 생성 실패
                    showToast(getString(R.string.failed_create_subject)) // 실패 Toast 출력
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
                    { _, hourOfDay, minute ->
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