package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.ScheduleAddActivity.Companion.INTENT_PUSH_ACTIVITY_CODE
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.ScheduleAddActivity.Companion.INTENT_PUSH_TOKEN_KEY
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import org.koin.androidx.viewmodel.ext.android.viewModel

class PushTimePickerActivity : BaseActivity<ActivityPushTimePickerBinding, PushTimeViewModel>() {

    override val layoutResId: Int = R.layout.activity_push_time_picker
    override val viewModel: PushTimeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity() // 액티비티 초기화
        setDefaultPushTime()
    }

    override fun initActivity() {
        initView()
    }

    fun initView() {
        with(binding) {
            setBindingData() // 데이터바인딩

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            // Finish 플래그가 True가 되면 액티비티를 종료한다.
            viewModel?.finishFlag?.observe(this@PushTimePickerActivity) { finishFlag ->
                if (finishFlag) {
                    finishWithPushTime()
                }
            }
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finishWithPushTime()
        }
        return true
    }

    override fun onBackPressed() {
        finishWithPushTime() // 푸시 타임 데이터와 함께 액티비티 종료
    }

    // 이전 액티비티로부터 전달받은 푸시값 설정
    private fun setDefaultPushTime() {
        // 이전 액티비티로부터 푸시타임을 전달받고, 전달받은 값이 없으면 None으로 설정
        viewModel.pushTime = intent.getSerializableExtra(INTENT_PUSH_TOKEN_KEY) as PushTime?

        // 우측에 체크 모양
        if (viewModel.pushTime != null) {
            val checkIcon = getDrawable(R.drawable.ic_checked)

            // 전달 받은 푸시 타임 라디오버튼에 체크 아이콘 표시
            when(viewModel.pushTime) {
                PushTime.NONE -> binding.radioAll.setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
                PushTime.TEN -> binding.radioTen.setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
                PushTime.THIRTY -> binding.radioThirty.setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
                PushTime.ONE_HOUR -> binding.radioOneHour.setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
                PushTime.THREE_HOUR -> binding.radioThreeHour.setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
            }
        }
    }

    // 푸시 타임 데이터와 함께 액티비티 종료
    private fun finishWithPushTime() {
        val pushData = Intent().putExtra(
            INTENT_PUSH_TOKEN_KEY,
            viewModel.pushTime
        ) // 종료시 ClassAddActivity로 전달할 푸시타임 데이터
        setResult(INTENT_PUSH_ACTIVITY_CODE, pushData) // Result 코드와 Intent 전달
        finish() // 액티비티 종료
    }


}