package com.dnd.sixth.lmsservice.presentation.main.classmanage.classes.create

import android.app.TimePickerDialog
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCreateClassBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.utility.TimeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClassCreateActivity : BaseActivity<ActivityCreateClassBinding, CreateClassViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_create_class
    override val viewModel: CreateClassViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setViewModel() // 뷰모델 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setViewModel() {
        binding.viewModel = this@ClassCreateActivity.viewModel
    }

    // 뷰 초기화
    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_black_close_btn) // 뒤로가기 버튼 변경
            setOnClickListener(this) // 클릭 리스너 설정

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
            }
        }
    }

    private fun setOnClickListener(binding: ActivityCreateClassBinding) {
        with(binding) {
            timeSelectContainer.setOnClickListener(this@ClassCreateActivity)
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
        }else{
            binding.amPmRadioGroup.check(R.id.am_radio_btn)
        }

        binding.hourTextView.text =
            getString(R.string.hour_or_minute_format, TimeConverter().convertHourInZeroPM(viewModel.hour))
        binding.minTextView.text =
            getString(R.string.hour_or_minute_format, TimeConverter().convertHourInZeroPM(viewModel.minute))
    }
}