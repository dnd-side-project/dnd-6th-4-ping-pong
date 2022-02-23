package com.dnd.sixth.lmsservice.presentation.main

import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityMainBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.ClassManageFragment
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarFragment
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData()
        initView()
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun initView() {
        with(binding) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, ClassManageFragment()).commitNow()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.fragments.any { it is CalendarFragment }) {
            // 뒤로가기 눌렀을 경우, 캘린더가 펼쳐져 있으면
            if (CalendarViewModel.isExpanded.value == true) {
                // 종료 경고 토스트 출력
                showFinishAlert()
            } else { // 접혀져 있었다면 isExpanded 를 'True' 로 바꾸어 캘린더를 펼쳐준다
                CalendarViewModel.expandCalendar()
            }
        } else { // 다른 Fragment의 경우에는, 뒤로가기 버튼 클릭시
            // 종료 경고 토스트 출력
            showFinishAlert()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    // 뒤로가기를 한 번 눌렀을 때, 바로 종료되지 않도록 경고 Toast 출력
    private fun showFinishAlert() {
        if (viewModel.canFinish()) {
            finish() // 액티비티 종료
        } else {
            showToast("한 번 더 누르면 종료됩니다.")
        }
    }

}