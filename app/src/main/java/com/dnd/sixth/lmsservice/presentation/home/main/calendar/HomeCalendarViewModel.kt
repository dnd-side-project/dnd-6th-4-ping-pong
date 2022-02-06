package com.dnd.sixth.lmsservice.presentation.home.main.calendar

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class HomeCalendarViewModel: BaseViewModel() {
    // 달력 월 리스트
    val monthList: Array<String>? = App.context?.resources?.getStringArray(R.array.month_list)

    companion object {
        // 캘린더가 펼쳐져 있는지 확인
        var isExpanded = MutableLiveData<Boolean>(true) // 기본값으로 true
    }
}