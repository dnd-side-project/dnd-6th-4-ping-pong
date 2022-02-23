package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import java.util.*

class CalendarViewModel : BaseViewModel() {
    // 달력 월 리스트
    private val monthStringList: Array<String>? = App.instance.context.resources?.getStringArray(R.array.month_string_list)
    private val monthIntegerList: IntArray? = App.instance.context.resources?.getIntArray(R.array.month_integer_list)

    // 선택된 수업 리스트
    private val _selectedClassList = MutableLiveData<List<DailyEntity>>()
    val selectedDailyEntityList: LiveData<List<DailyEntity>> = _selectedClassList

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
    fun transPosIntoMonth(position: Int): Int = monthIntegerList?.get(position) ?: getCurrentDate().month

    // 현재 선택한 날짜와 입력받은 날짜가 같은지 비교
    fun isSameDate(date: Date): Boolean {
        return date == currentDate
    }

    // 선택된 날짜의 수업 개수를 가져온다.
    fun getSelectedClassCount() = _selectedClassList.value?.size ?: 0
}