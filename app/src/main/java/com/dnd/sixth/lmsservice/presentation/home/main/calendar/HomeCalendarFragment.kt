package com.dnd.sixth.lmsservice.presentation.home.main.calendar

import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentHomeCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.add.ScheduleAddActivity
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.decorator.MySelectorDecorator
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.decorator.ScheduleDecorator
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.decorator.TodayDecorator
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeCalendarFragment : BaseFragment<FragmentHomeCalendarBinding, HomeCalendarViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_home_calendar

    //뷰모델 추가
    override val viewModel: HomeCalendarViewModel by viewModel()
    private var categoryDialog: BottomSheetDialog? = null

    //액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@HomeCalendarFragment.viewModel

            calendarView.topbarVisible = false // 캘린더 헤더 가리기
            setSpinner(monthSpinner) // 스피너 설정
            makeStudentCategoryDialog() // 학생 선택 다이얼로그 생성
            setClickListener(this)
            setCalendar() // 캘린더 관련 설정
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
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

        // 스피너의 Month를 클릭하면 캘린더의 월을 변경한다
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // 선택한 월(Month)의 캘린더를 보여준다.

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun makeStudentCategoryDialog() {
        val sheetView = layoutInflater.inflate(R.layout.layout_student_category_bottom_sheet, null)
        categoryDialog = BottomSheetDialog(requireContext()).apply {
            setContentView(sheetView)
        }
    }

    private fun setClickListener(binding: FragmentHomeCalendarBinding) {
        with(binding) {
            scheduleAddFab.setOnClickListener(this@HomeCalendarFragment)
            showCategoryBtn.setOnClickListener(this@HomeCalendarFragment)
        }
    }

    private fun setCalendar() {
        with(binding.calendarView) {

            // 날짜 클릭 시 이벤트
            setOnDateChangedListener { widget, date, selected ->
                // 캘린더의 날짜를 클릭할 경우 하단의 텍스트를 현재 날짜로 변경
                binding.selectedDateTextView.text = getString(
                    R.string.calendar_date_format,
                    date.year,
                    date.month + 1,
                    date.day,
                    DateConverter().getDayOfWeek(date.calendar[Calendar.DAY_OF_WEEK])
                )

                // 한 주 단위로 캘린더를 보여줌
                state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit()
                // 확장 여부 false 로 변경
                HomeCalendarViewModel.isExpanded.value = false
            }

            // 캘린더의 Month가 달라지면 실행되는 리스너
            setOnMonthChangedListener { widget, date ->
                // Month가 변함에 따라 스피너의 Month 값도 변경
                binding.monthSpinner.setSelection(date.month)
            }

            // 한 달 단위로 캘린더를 보여줌
            //state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit()


            val temp1 = Calendar.getInstance().apply {
                set(2022, 1, 7)
            }
            val temp2 = Calendar.getInstance().apply {
                set(2022, 1, 10)
            }

            val tempEventDate = listOf<CalendarDay>(
                CalendarDay.from(temp1),
                CalendarDay.from(temp2)
            )

            // 캘린더 데코레이터 지정
            addDecorators(
                MySelectorDecorator(requireActivity()),
                ScheduleDecorator(R.color.colorPrimary, tempEventDate),
                TodayDecorator(resources),
            )

            // 캘린더 확장 여부 관찰
            HomeCalendarViewModel.isExpanded.observe(this@HomeCalendarFragment) { isNewlyExpanded ->
                // 새롭게 변경된 상태 값이 'Expand' 이면
                if (isNewlyExpanded) {
                    // 캘린더를 펼쳐서 월 단위로 캘린더를 보여준다
                    state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit()
                }
            }

        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.schedule_add_fab -> startActivity(
                Intent(
                    requireContext(),
                    ScheduleAddActivity::class.java
                )
            )
            R.id.show_category_btn -> {
                categoryDialog?.show()
            }
        }
    }


}