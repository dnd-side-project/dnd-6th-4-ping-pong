package com.dnd.sixth.lmsservice.presentation.home.main.calendar.notipicker

import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class PushTimePickerViewModel: BaseViewModel() {

    // 푸시 타임 시간별 리스트
    val pushTimeList: Array<String> = App.context?.resources?.getStringArray(R.array.push_time_list)!!
    var pickedNotificationTime: String? = null

    // 과외 알림 노티피케이션 날짜 텍스트 설정
    fun pickNotificationTime(index: Int) {
        pickedNotificationTime = pushTimeList[index]
    }
}