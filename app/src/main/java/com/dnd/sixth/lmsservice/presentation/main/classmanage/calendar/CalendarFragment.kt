package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.main.classmanage.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.ScheduleAddActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.MySelectorDecorator
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.ScheduleDecorator
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.TodayDecorator
import com.dnd.sixth.lmsservice.presentation.utility.DateConverter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jaredrummler.materialspinner.MaterialSpinner
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_calendar

    //뷰모델 추가
    override val viewModel: CalendarViewModel by viewModel()
    private val hostViewModel: ClassManageViewModel by sharedViewModel()

    private var categoryDialog: BottomSheetDialog? = null
    var viewTreeObserver: ViewTreeObserver? = null

    // 최상위 ViewTreeObserver (높이를 구하기 위한 변수)
    // var viewTreeObserver: ViewTreeObserver? = null

    //액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel
        binding.hostViewModel = hostViewModel // ViewModel 바인딩
    }

    private fun initView() {
        with(binding) {

            calendarView.topbarVisible = false // 캘린더 헤더 가리기
            setSpinner(monthSpinner) // 스피너 설정
            makeStudentCategoryDialog() // 학생 선택 다이얼로그 생성
            setClickListener(this)
            setCalendar() // 캘린더 관련 설정

        }


        // 뒤로가기 버튼 클릭시 콜백 (HomeActivity 와 연결되어 있음)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
    }

    private fun makeStudentCategoryDialog() {
        val sheetView = layoutInflater.inflate(R.layout.layout_student_category_bottom_sheet, null)
        categoryDialog = BottomSheetDialog(requireContext()).apply {
            setContentView(sheetView)
        }
    }

    private fun setClickListener(binding: FragmentCalendarBinding) {
        with(binding) {
            scheduleAddFab.setOnClickListener(this@CalendarFragment)
            showCategoryBtn.setOnClickListener(this@CalendarFragment)
        }
    }

    /* 월(Month) 스피너 설정 */
    private fun setSpinner(spinner: MaterialSpinner) {
        // 달력 Spinner 설정정
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.month_string_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(adapter)
        }

        // 스피너의 Month를 클릭하면 캘린더의 월을 변경한다
        spinner.setOnItemSelectedListener { view, position, id, item ->
            if (viewModel.transPosIntoMonth(position) != viewModel.getCurrentDate().month && viewModel.isDone) {
                viewModel.isDone = false
                // 선택된 Month를 바탕으로 ViewModel의 현재 Date 갱신
                viewModel.updateMonthByPosition(position)
                // 캘린더의 날짜 갱신
                binding.calendarView.setCurrentDate(viewModel.getCurrentDate())
                viewModel.isDone = true
            }
        }

    }


    @SuppressLint("ResourceType")
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

                // 특정 날짜를 클릭하면 캘린더를 접는다.
                CalendarViewModel.collapseCalendar()

                // 날짜 클릭시 View 높이 재측정
                setClassHomeScrollViewHeight()
            }

            // 캘린더의 Month가 달라지면 실행되는 리스너
            setOnMonthChangedListener { widget, newDate ->
                //ViewModel의 현재 Date 갱신
                viewModel.updateCurrentDate(newDate.date)

                // Month가 변함에 따라 스피너의 선택된 Month 변경
                // Month 는 실제 월보다 1작음 (따라서 바로 index로 사용해도 됨.)
                binding.monthSpinner.selectedIndex = (newDate.month)

                // 캘린더의 날짜 갱신
                widget.currentDate = newDate
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



            val temp3 = Calendar.getInstance().apply {
                set(2022, 1, 8)
            }
            val temp4 = Calendar.getInstance().apply {
                set(2022, 1, 20)
            }

            val tempEventDate2 = listOf<CalendarDay>(
                CalendarDay.from(temp3),
                CalendarDay.from(temp4)
            )


            /*  캘린더 데코레이터 지정
            *   ScheduleDecorator 아이디어 : 수업 종류만큼 ScheduleDecorator 를 추가한다.
            *   First Params : 수업 Id와 (1일 단위)수업에 들어있는 수업 Id를 비교하여 배열을 만들어 전달.
            *   Second Params : 수업 Model(ClassItem)로부터 해당 수업에 지정된 색상을 가져와 전달
            * */
            addDecorators(
                MySelectorDecorator(R.drawable.bg_calendar_selected_date, viewModel),
                ScheduleDecorator(tempEventDate, DateColor.DARK_BLUE),
                ScheduleDecorator(tempEventDate2, DateColor.ORANGE),
                TodayDecorator(R.color.secondMainColor),
            )

            // 캘린더 확장 여부 관찰
            CalendarViewModel.isExpanded.observe(this@CalendarFragment) { isExpand ->
                // 스크롤 가능한 화면 높이 설정
                setClassHomeScrollViewHeight()

                // 새롭게 변경된 상태 값이 'Expand' 이면
                if (isExpand) {
                    // 캘린더를 펼쳐서 월 단위로 캘린더를 보여준다
                    state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit()
                    binding.noScheduleContainer.visibility = View.GONE
                } else {
                    binding.noScheduleContainer.visibility = View.VISIBLE
                }
            }

        }

    }


    // 해당 Fragment의 높이를 구하여 ClassHomeFragment의 ScrollView 높이로 지정
    private fun setClassHomeScrollViewHeight() {
        viewTreeObserver = binding.mainContainer.viewTreeObserver
        viewTreeObserver?.addOnGlobalLayoutListener(viewTreeObserverCallback)
        viewTreeObserverCallback.onGlobalLayout()
    }


    override fun onResume() {
        super.onResume()
        // Host Fragment의 ScrollView 높이 재설정
        setClassHomeScrollViewHeight()
    }

    private val viewTreeObserverCallback = object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {

            // ClassHomeFragment의 ViewPager ScrollView에 지정할 높이 구하기
            val sumHeight =
                // Month(월) Spinner 높이
                binding.monthSpinner.measuredHeight +
                        // 캘린더 뷰 높이
                        binding.calendarView.measuredHeight +
                        // 스크롤뷰 높이 (후에 리사이클러뷰가 추가되면 ViewHolder 높이에 따라 개수만큼 곱해줘야 함)
                        binding.noScheduleScrollView.measuredHeight * viewModel.getSelectedClassCount()

            ClassManageViewModel.screenHeight.value = sumHeight
            Timber.tag("CalendarFragment Height").d("$sumHeight")

            try {
                // viewTreeObserver 제거
                if (binding.monthSpinner.measuredHeight > 0) {
                    viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }
            } catch (e: IllegalStateException) {
                if (BuildConfig.DEBUG) {
                    Timber.d("ViewTree를 한 번만 실행시키기 위해 제거했기 때문에 발생하는 예외")
                }
                return
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