package com.dnd.sixth.lmsservice.presentation.main.schedule.calendar

import android.view.View
import android.widget.*
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import kotlinx.android.synthetic.main.fragment_class_calendar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassCalendarFragment : BaseFragment<FragmentClassCalendarBinding, ClassCalendarViewModel>() {
    override val layoutResId: Int
        get() = R.layout.fragment_class_calendar

    //뷰모델 추가
    override val viewModel: ClassCalendarViewModel by viewModel()


    //액티비티 초기화 메서드
    override fun initActivity() {
        //
        with(binding) {
            viewModel = this@ClassCalendarFragment.viewModel

            calendarView.topbarVisible = false // 캘린더 헤더 가리기

            // 달력 Spinner 설정정
           ArrayAdapter.createFromResource(
                requireContext(),
                R.array.month_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                monthSpinner.setAdapter(adapter)
            }

        }


    }


}