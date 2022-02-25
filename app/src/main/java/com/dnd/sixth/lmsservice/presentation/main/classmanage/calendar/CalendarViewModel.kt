package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassListUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import java.util.*

class CalendarViewModel(
    private val getDailyClassListUseCase: GetDailyClassListUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {
    // 달력 월 리스트
    private val monthStringList: Array<String>? =
        App.instance.context.resources?.getStringArray(R.array.month_string_list)
    private val monthIntegerList: IntArray? =
        App.instance.context.resources?.getIntArray(R.array.month_integer_list)

    // 사용자가 캘린더 날짜를 터치하여 선택된 수업 리스트 (타임라인 시각화 용도)
    private val _selectedDailyClassList = MutableLiveData<List<DailyEntity>>()
    val selectedDailyEntityList: LiveData<List<DailyEntity>> = _selectedDailyClassList

    // 캘린더에 보여줄 유저의 일일 수업 리스트
    private val _dailyClassList = MutableLiveData<MutableList<DailyEntity>?>(
    )
    val dailyClassList: LiveData<MutableList<DailyEntity>?> = _dailyClassList

    // 유저가 선택한 카테고리
    // (Subject Id 값을 전달받습니다.)
    // 카테고리에 따라 CalendarView의 Dot을 필터링해서 보여줍니다.
    val selectedSubjectCategory = MutableLiveData<Int>(CalendarFragment.CATEGORY_ALL)

    init {
        onIO {
            // ViewModel 생성시 서버로부터 사용자의 일일 수업 리스트를 가져옵니다.
            /*_dailyClassList.postValue(
                getDailyClassListUseCase(preferenceManager.getInt(SAVED_UID_KEY))?.toMutableList())*/
            // 더미값(나중에 삭제)
            _dailyClassList.postValue(
                mutableListOf<DailyEntity>(
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-11 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-2 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-9 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-16 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-23 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-04 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                    DailyEntity(
                        0,
                        1,
                        0,
                        "2022-02-18 03:50",
                        "",
                        "",
                        "",
                        "",
                    ),
                )
            )
        }
    }


    /*
    *  파라미터로 입력받은 subjectId의 DateColor를 반환합니다.
    * */
    fun getDateColorOf(findSubjectId: Int, dateColorMap: Map<Int, DateColor>): DateColor? {
        var foundDateColor: DateColor? = null
        dateColorMap.forEach { (subjectId, dateColor) ->
            if (subjectId == findSubjectId) {
                foundDateColor = dateColor
                return@forEach
            }
        }

        return foundDateColor
    }


    private var currentDate: Date = Date()
    var isDone = true // 캘린더 관련 데이터 업데이트 완료 여부

    companion object {
        // 캘린더가 펼쳐져 있는지 확인
        private val _isExpanded = MutableLiveData<Boolean>(true) // 기본값으로 true
        val isExpanded: LiveData<Boolean> // 기본값으로 true
            get() = _isExpanded

        // Observer를 통해 true로 변경시 캘린더를 펼친다
        fun expandCalendar() {
            _isExpanded.value = true
        }

        // Observer를 통해 false로 변경시 캘린더를 접는다
        fun collapseCalendar() {
            _isExpanded.value = false
        }
    }


    // 현재 보고 있는 날짜 변경
    fun updateCurrentDate(newDate: Date) {
        currentDate = newDate
    }

    // 현재 화면의 Month를 업데이트
    fun updateMonthByPosition(monthPosition: Int) {
        // Position은 0부터 시작하므로 1 더해서 현재 달력의 월(Month)을 업데이트한다.
        val newMonth = monthPosition + 1
        currentDate.month = newMonth
    }

    // 현재 바라보고 있는 Date를 가져온다
    fun getCurrentDate(): Date = currentDate

    // Spinner의 position을 바탕으로 Month를 가져온다
    fun transPosIntoMonth(position: Int): Int =
        monthIntegerList?.get(position) ?: getCurrentDate().month

    // 현재 선택한 날짜와 입력받은 날짜가 같은지 비교
    fun isSameDate(date: Date): Boolean {
        return date == currentDate
    }

    // 선택된 날짜의 수업 개수를 가져온다.
    fun getSelectedClassCount() = _selectedDailyClassList.value?.size ?: 0

    // SubjectId에 해당하는 수업들만 가져옵니다.
    fun getDailyClassesById(subjectId: Int) =
        _dailyClassList.value?.filter {
            it.subjectId == subjectId
        }

    fun addDailyEntity(dailyEntity: DailyEntity) {
        _dailyClassList.value = _dailyClassList.value.apply {
            this!!.add(dailyEntity)
        }
    }
}