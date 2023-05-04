package com.dnd.sixth.lmsservice.presentation.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TimeConverter {

    fun getAmPm(date: Calendar): String {
        val isPM = date.get(Calendar.AM_PM)
        return if (isPM == 1) {
            return "오후"
        } else {
            "오전"
        }
    }

    fun getAmPm(hour: Int): String {
        return if(hour < 12) {
            "오전"
        }else {
            "오후"
        }
    }

    /*  시간을 오후 시간대로 변환 (00시는 12시로 변환)
         *  @param hour : 시간대 (0 ~ 24)
         *  @return (hour = 13) -> 1
         */
    @SuppressLint("SimpleDateFormat")
    fun convertPmHour(hour: Int): Int {
        val hourInPM = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }
        return hourInPM
    }

    /*  시간을 오후 시간대로 변환 (00시는 그대로 00시로 반환)
     *  @param hour : 시간대 (0 ~ 24)
     *  @return (hour = 13) -> 1, (hour = 0) -> 0
     */
    fun convertHourPmIncludedZero(hour: Int): Int {
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