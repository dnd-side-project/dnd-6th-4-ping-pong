package com.dnd.sixth.lmsservice.presentation.main.schedule.calendar

import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class HomeCalendarViewModel: BaseViewModel() {
    // 달력 월 리스트
    val monthList: Array<String>? = App.context?.resources?.getStringArray(R.array.month_list)
}