package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.request

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import java.util.*

class EditRequestViewModel: BaseViewModel() {

    // 일정 : DateTimePicker로 선택한 날짜의 Calendar 데이터
    val pickedDate = MutableLiveData<Date>(null)

    // 수업 회차 기본값
    private val defaultClassRound = 1

    // 수업 회차 LiveData
    var classRound = MutableLiveData<Int>(defaultClassRound)

}