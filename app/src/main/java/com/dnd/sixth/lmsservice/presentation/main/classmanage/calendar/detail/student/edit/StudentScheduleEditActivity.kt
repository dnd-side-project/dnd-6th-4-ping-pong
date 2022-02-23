package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.edit

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityStudentScheduleEditBinding
import com.dnd.sixth.lmsservice.databinding.DialogPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.PushTimePickerActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.request.EditRequestActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class StudentScheduleEditActivity :
    BaseActivity<ActivityStudentScheduleEditBinding, StudentScheduleEditViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_student_schedule_edit
    override val viewModel: StudentScheduleEditViewModel by viewModel()
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null // 푸시 타임 피커 액티비티 런쳐

    companion object {
        const val INTENT_PUSH_TOKEN_KEY = "newPushToken"
        const val INTENT_PUSH_ACTIVITY_CODE = 1000
        const val INTENT_EDIT_REQUEST_ACTIVITY_CODE = 2000
    }

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

            setListener(this) // 리스너 설정
            setActivityLauncher() // 액티비티 런처 설정

            DialogPushTimePickerBinding.inflate(layoutInflater)


            // 푸시타임 데이터가 변경되면 텍스트 및 색상을 변경한다.
            viewModel?.pushTime?.observe(this@StudentScheduleEditActivity) { pushTime ->
                if (pushTime == null) {
                    return@observe
                }
                notiTextView.isEnabled = true // 색상 변경을 위해 활성화 True
                notiTextView.text = pushTime.timeText // 푸시 알림 텍스트뷰 갱신
            }
        }
    }

    private fun setActivityLauncher() {
        // 푸시 타임 피커 액티비티 런쳐
        activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == INTENT_PUSH_ACTIVITY_CODE) {
                    val data = result.data // 푸시 타임 데이터
                    val newPushTime: PushTime? =
                        data?.getSerializableExtra(INTENT_PUSH_TOKEN_KEY) as PushTime?
                    viewModel.changePushTime(newPushTime) // 새로 받아온 푸시 타임으로 갱신
                } else if (result.resultCode == INTENT_PUSH_ACTIVITY_CODE)
                    if (result.resultCode == INTENT_EDIT_REQUEST_ACTIVITY_CODE) {
                        val data = result.data // 변경된 일정 데이터

                        /* 데이터를 바탕으로 화면을 다시 그림 */

                    }
            }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
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
            R.id.class_noti_container, R.id.noti_down_arrow_btn -> { // 푸시 타임 선택할 수 있는 액티비티로 이동
                // 현재 푸시 타임 데이터 전달
                val intent = Intent(this, PushTimePickerActivity::class.java).putExtra(
                    INTENT_PUSH_TOKEN_KEY, viewModel.pushTime.value
                )
                activityResultLauncher?.launch(intent) // 런처를 통해 푸시 선택 액티비티 선택
            }
            R.id.edit_request_btn -> { // 일정 변경 요청 화면으로 이동
                val intent = Intent(this, EditRequestActivity::class.java)
                activityResultLauncher?.launch(intent)
            }
        }
    }

    private fun setListener(binding: ActivityStudentScheduleEditBinding) {
        with(binding) {
            dateContainer.setOnClickListener(this@StudentScheduleEditActivity)
            classRoundContainer.setOnClickListener(this@StudentScheduleEditActivity)
            classNotiContainer.setOnClickListener(this@StudentScheduleEditActivity)
            notiDownArrowBtn.setOnClickListener(this@StudentScheduleEditActivity)
            editRequestBtn.setOnClickListener(this@StudentScheduleEditActivity)
        }
    }


    // DateTimePicker 변경시 해당 메서드로 Calendar 객체를 전달하여
    // 화면 갱신
    /* private fun setDateTimeText(date: Calendar) {
         binding.dateTimeTextView.text = DateConverter().getFullDate(date.time) // 날짜 형식 변환
     }*/


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