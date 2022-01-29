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

    fun getHourInPM(hour: Int): Int {
        val hourInPM = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }
        return hourInPM
    }
}