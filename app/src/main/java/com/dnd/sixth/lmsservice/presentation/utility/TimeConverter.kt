package com.dnd.sixth.lmsservice.presentation.utility

import java.util.*

class TimeConverter {

    fun getAMPM(date: Calendar): String {
        val isPM = date.get(Calendar.AM_PM)
        return if (isPM == 1) {
            return "오후"
        } else {
            "오전"
        }
    }

    // 시간을 오후 시간대로 변환
    // 00시는 12시로 변환
    fun convertHourInPM(hour: Int): Int {
        val hourInPM = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }
        return hourInPM
    }

    // 시간을 오후 시간대로 변환
    // 00시는 그대로 00시로 반환
    fun convertHourInZeroPM(hour: Int): Int {
        val hourInPM = when {
            hour > 12 -> hour - 12
            else -> hour
        }
        return hourInPM
    }

    fun isPM(hour: Int): Boolean {
        return hour >= 12
    }
}