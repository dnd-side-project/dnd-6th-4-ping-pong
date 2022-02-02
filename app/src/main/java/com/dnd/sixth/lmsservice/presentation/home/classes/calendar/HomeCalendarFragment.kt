package com.dnd.sixth.lmsservice.presentation.home.classes.calendar

import android.widget.*
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentHomeCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeCalendarFragment : BaseFragment<FragmentHomeCalendarBinding, HomeCalendarViewModel>() {
    override val layoutResId: Int
        get() = R.layout.fragment_home_calendar

    //뷰모델 추가
    override val viewModel: HomeCalendarViewModel by viewModel()


    //액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@HomeCalendarFragment.viewModel

            calendarView.topbarVisible = false // 캘린더 헤더 가리기
            setSpinner(monthSpinner) // 스피너 서렂ㅇ
        }
    }

    private fun setSpinner(spinner: Spinner) {
        // 달력 Spinner 설정정
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.month_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        setSpinnerHeight(spinner)
    }

    private fun setSpinnerHeight(spinner: Spinner) {
        try {
            val popup = Spinner::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true

            val window: ListPopupWindow = popup.get(spinner) as ListPopupWindow
            window.height = 100
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

}