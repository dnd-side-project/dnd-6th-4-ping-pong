package com.dnd.sixth.lmsservice.presentation.main.schedule.edit

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import java.util.*

class ScheduleEditViewModel: BaseViewModel() {

    // 일정 : DateTimePicker로 선택한 날짜의 Calendar 데이터
    var pickedDate: Calendar? = null

    // 수업 회차 기본값
    private val defaultClassRound = 1
    // 수업 회차 LiveData
    var classRound = MutableLiveData<Int>(defaultClassRound)


    // 수업 회차 value + 1
    fun plusClassRound() {
        classRound.value = classRound.value?.plus(1)
    }

    // 수업 회차 value - 1
    fun minusClassRound() {
        classRound.value.let {
            // 수업 회차가 초기값(1)보다 클 때만 갱신한다
            if (it!! > defaultClassRound) {
                classRound.value = it.minus(1)
            }
        }
    }

    // 수업 회차를 특정 값으로 설정
    fun setClassRound(num: Int) {
        classRound.value = num
    }

    // 수업 회차를 기본 값으로 설정
    fun setDefaultClassRound() {
        classRound.value = defaultClassRound
    }

}