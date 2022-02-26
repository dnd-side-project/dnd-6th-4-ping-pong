package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.databinding.FragmentCalendarBinding
import com.dnd.sixth.lmsservice.databinding.ItemSubjectCategoryRadioButtonBinding
import com.dnd.sixth.lmsservice.databinding.LayoutStudentCategoryBottomSheetBinding
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.TimeLineAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.extensions.removeAllDotDecorators
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.ScheduleAddActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.MySelectorDecorator
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.ScheduleDecorator
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator.TodayDecorator
import com.dnd.sixth.lmsservice.presentation.utility.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jaredrummler.materialspinner.MaterialSpinner
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import kotlinx.android.synthetic.main.layout_student_category_bottom_sheet.*
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

    private lateinit var scheduleAddActivityLauncher: ActivityResultLauncher<Intent>
    private var colorDecorators = mutableListOf<ScheduleDecorator>()

    companion object {
        const val INTENT_CREATE_DAILY_ENTITY_KEY = "createDaily"
        const val INTENT_SUBJECT_ID_TO_USER_NAME_MAP_KEY = "subjectIdToUserNameMap"

        const val INTENT_CREATE_DAILY_ACTIVITY_CODE = 3000

        const val CATEGORY_ALL = -1 // 전체보기 라디오 버튼의 ID
    }

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
            setActivityLauncher() // 액티비티 런처 설정
            setViewVisibility() // 유저 상태에 따라 View의 Visibillity 설정
        }

        initRecyclerView()


        // 뒤로가기 버튼 클릭시 콜백 (HomeActivity 와 연결되어 있음)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
    }

    // 유저 상태에 따라 View의 Visibillity 설정
    private fun setViewVisibility() {
        // 등록된 수업(Subject)가 없고,
        // 학생 유저이면
        // 추가버튼(Fab)을 안보이게 한다.
        val preferenceManager = PreferenceManager(requireContext())
        val role = preferenceManager.getInt(SAVED_ROLE_KEY)

        if (hostViewModel.hasClass().not() || role == ROLE_STUDENT) {
            binding.scheduleAddFab.visibility = View.GONE
        } else {
            binding.scheduleAddFab.visibility = View.VISIBLE
        }
    }

    private fun setActivityLauncher() {

        scheduleAddActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK) {
                val resultData = data?.getSerializableExtra(INTENT_CREATE_DAILY_ENTITY_KEY) as DailyEntity
                viewModel.addDailyEntity(resultData)
            }
        }
    }

    private fun makeStudentCategoryDialog() {
        val sheetView = LayoutStudentCategoryBottomSheetBinding.inflate(layoutInflater)
        categoryDialog = BottomSheetDialog(requireContext()).apply {
            setContentView(sheetView.root)
            addCategoryRadioButtonViews(sheetView)
        }

        viewModel.selectedSubjectCategory.observe(this) { selectedSubjectId ->
            // 선택한 SubjectId만 보이도록 CalendarView를 필터링합니다.
            filterCalendarDot(selectedSubjectId)

            // Radio Button Group을 순회하면서 선택된 Radio Button에 Check아이콘 설정
            sheetView.studentCategoryRadioGroup.children.iterator().forEach {
                val checkIcon = getDrawable(requireContext(), R.drawable.ic_checked)
                if (it.id == selectedSubjectId){ // 선택된 RadioButton
                    (it as RadioButton).setCompoundDrawablesWithIntrinsicBounds(null, null, checkIcon, null)
                } else { // 선택되지 않은 RadioButton
                    (it as RadioButton).setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                }
            }
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


    // 학생 선택 라디오버튼 추가
    private fun addCategoryRadioButtonViews(categoryBinding: LayoutStudentCategoryBottomSheetBinding) {

        // 전체보기 라디오버튼 리스너 설정
        categoryBinding.radioAll.id = CATEGORY_ALL
        categoryBinding.radioAll.setOnClickListener {
            // 전체보기 라디오버튼이 선택이 안돼서 임의로 클릭리스너를 통해 지정
            categoryBinding.studentCategoryRadioGroup.check(R.id.radio_all)
            binding.categoryTextView.text = "전체보기"

            // 현재 선택한 수업 카테고리의 RadioId 값으로 'CATEGORY_ALL'을 전달합니다.
            viewModel.selectedSubjectCategory.value = CATEGORY_ALL

            categoryDialog?.dismiss()
        }

        // 전체보기를 제외한 동적으로 추가한 라디오버튼 설정
        hostViewModel.generalSubjectDataList.value?.forEach { generalSubjectData ->

            // 동적으로 라디오버튼을 생성하여 라디오그룹에 추가합니다.
            val subjectRadioButton =
                ItemSubjectCategoryRadioButtonBinding.inflate(layoutInflater).run {
                    studentRadioButton.apply {
                        // 카테고리별 버튼 타이틀 지정
                        val radioTitle = getString(R.string.category_title_format,
                            generalSubjectData.otherName,
                            generalSubjectData.subjectName
                        )
                        val subjectId = generalSubjectData.subjectId.toInt()

                        text = radioTitle
                        id = subjectId
                        setOnClickListener {
                            binding.categoryTextView.text = radioTitle
                        }
                    }
                }

            // RadioGroup Child Width, Height을 설정합니다.
            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                UnitConverter.convertDPtoPX(requireContext(), 56)
            )
            categoryBinding.studentCategoryRadioGroup.addView(subjectRadioButton, params)
        }

        categoryBinding.studentCategoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            // 현재 선택한 수업 카테고리의 RadioId 값을 전달합니다.
            viewModel.selectedSubjectCategory.value = checkedId
            categoryDialog?.dismiss()
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

            // 학생 일일 수업 리스트 변경시 캘린더에 Dot을 새로 Mark합니다.
            viewModel.dailyClassList.observe(this@CalendarFragment) { dailyEntityList ->
                val redDate = mutableListOf<CalendarDay>()
                val orgDate = mutableListOf<CalendarDay>()
                val yelDate = mutableListOf<CalendarDay>()
                val grnDate = mutableListOf<CalendarDay>()
                val bluDate = mutableListOf<CalendarDay>()
                val dBluDate = mutableListOf<CalendarDay>()
                val pplDate = mutableListOf<CalendarDay>()

                // 해당 유저의 수업별로 컬러를 지정한 Map을 순회하며 CalendarDay 리스트를 채워나갑니다.
                hostViewModel.getDateColorMap().forEach { (subjectId, dateColor) ->
                    dailyEntityList?.forEach { dailyEntity ->
                        if (dailyEntity.subjectId == subjectId) {
                            when (dateColor) {
                                DateColor.RED -> redDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.ORANGE -> orgDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.YELLOW -> yelDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.GREEN -> grnDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.BLUE -> bluDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.DARK_BLUE -> dBluDate.add(generateCalendar(dailyEntity.startTime))
                                DateColor.PURPLE -> pplDate.add(generateCalendar(dailyEntity.startTime))
                            }
                        }
                    }
                }

                colorDecorators.add(ScheduleDecorator(redDate, DateColor.RED))
                colorDecorators.add(ScheduleDecorator(orgDate, DateColor.ORANGE))
                colorDecorators.add(ScheduleDecorator(yelDate, DateColor.YELLOW))
                colorDecorators.add(ScheduleDecorator(grnDate, DateColor.GREEN))
                colorDecorators.add(ScheduleDecorator(bluDate, DateColor.BLUE))
                colorDecorators.add(ScheduleDecorator(dBluDate, DateColor.DARK_BLUE))
                colorDecorators.add(ScheduleDecorator(pplDate, DateColor.PURPLE))

                // 선택 학생 카테고리에 따른 색상 필터링
                filterCalendarDot(viewModel.selectedSubjectCategory.value!!)
            }


            /*  캘린더 데코레이터 지정
            *   ScheduleDecorator 아이디어 : 수업 종류만큼 ScheduleDecorator 를 추가한다.
            *   First Params : 수업 Id와 (1일 단위)수업에 들어있는 수업 Id를 비교하여 배열을 만들어 전달.
            *   Second Params : 수업 Model(ClassItem)로부터 해당 수업에 지정된 색상을 가져와 전달
            * */
            addDecorators(
                MySelectorDecorator(R.drawable.bg_calendar_selected_date),
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

    // 선택한 SubjectId만 보이도록 CalendarView를 필터링합니다.
    private fun filterCalendarDot(selectedSubjectId: Int) {
        with(binding.calendarView) {
            // 우선적으로 기존의 Dot 데코레이터를 모두 제거합니다.
            removeAllDotDecorators()

            // 전체보기를 선택한 경우
            if (selectedSubjectId == CATEGORY_ALL) {
                // 모든 수업 일정 Decorator를 표시합니다.
                addDecorators(colorDecorators)
            } else {
                // 현재 선택한 수업(Subject)의 Id와 색상 Map을 전달하여
                // 선택한 수업의 DateColor를 반환합니다.
                val dateColor: DateColor =
                    viewModel.getDateColorOf(selectedSubjectId, hostViewModel.getDateColorMap())
                        ?: DateColor.DARK_BLUE

                // 필터링한 CalendarDay를 담을 리스트입니다.
                val filteredCalendarDayList = mutableListOf<CalendarDay>()

                // ViewModel로부터 SubjectId에 해당하는 일일 수업 리스트를 받아와 CalendarDay 객체를 생성합니다.
                viewModel.getDailyClassesById(selectedSubjectId)?.forEach {
                    filteredCalendarDayList.add(generateCalendar(it.startTime))
                }

                // 선택한 수업의 단일 데코레이터 추가
                addDecorator(
                    ScheduleDecorator(
                        filteredCalendarDayList,
                        dateColor
                    )
                )
            }
        }

    }


    /*
    * @param: classDateTime -> "2022-02-22 01:01
    * @return: Calendar (for marking dot on calendar view)
    * */
    private fun generateCalendar(classDateTime: String): CalendarDay {
        val date = classDateTime.split(" ")[0].split("-") // Format : (2022, 02, 22)
        val year = date[0].toInt()
        val month = date[1].toInt() - 1 // (Calendar는 month 범위가 0~11입니다.)
        val day = date[2].toInt()

        return CalendarDay.from(Calendar.getInstance().apply {
            set(year, month, day)
        })
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
            // 수업과목 Id와 유저이름 HashMap을 함께 전달
            R.id.schedule_add_fab -> scheduleAddActivityLauncher.launch(
                Intent(
                    requireContext(),
                    ScheduleAddActivity::class.java
                ).putExtra(
                    INTENT_SUBJECT_ID_TO_USER_NAME_MAP_KEY,
                    hostViewModel.getUserNameMap()
                )
            )
            R.id.show_category_btn -> {
                categoryDialog?.show()
            }
        }
    }
    fun initRecyclerView(){
        var adapter = TimeLineAdapter() { feedbackBtn ->

        }
        binding.timelineRecyclerView.adapter = adapter
        binding.timelineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.timelineRecyclerView.visibility = View.VISIBLE


        adapter.timeLineList =
            //더미데이터
            mutableListOf(
            DailyClassEntity(
                1, 3, "10:30", "강남역 투썸플레이스", "수학",
                "", "", "", "", "", false, 02.30, 0, 3
            ), DailyClassEntity(
                1, 2, "10:30", "강남역 투썸플레이스", "수학",
                "", "", "", "", "", false, 02.23, 0, 2
            ),
            DailyClassEntity(
                1, 1, "10:30", "강남역 투썸플레이스", "수학",
                "", "", "", "", "", false, 02.16, 0, 1
            )
        )

    }


}