package com.dnd.sixth.lmsservice.presentation.home.classes.calendar

import android.content.Intent
import android.view.View
import android.widget.*
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentHomeCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.add.ClassAddActivity
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.edit.ScheduleEditDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeCalendarFragment : BaseFragment<FragmentHomeCalendarBinding, HomeCalendarViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_home_calendar

    //뷰모델 추가
    override val viewModel: HomeCalendarViewModel by viewModel()


    //액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@HomeCalendarFragment.viewModel

            calendarView.topbarVisible = false // 캘린더 헤더 가리기
            setSpinner(monthSpinner) // 스피너 설정
            setClickListener(this)
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
    }

    private fun setClickListener(binding: FragmentHomeCalendarBinding) {
        with(binding) {
            scheduleAddFab.setOnClickListener(this@HomeCalendarFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.schedule_add_fab -> startActivity(
                Intent(
                    requireContext(),
                    ClassAddActivity::class.java
                )
            )
        }
    }


}