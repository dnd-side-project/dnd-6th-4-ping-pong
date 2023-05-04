package com.dnd.sixth.lmsservice.presentation.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_WEEK

class DateConverter {

    /*
    *  Calendar 객체의 DAY_OF_WEEK의 Value값으로 요일을 구함 (일 ~ 토)
    *  @param dayOfWeek : Calendar 객체의 DAY_OF_WEEK(Key)의 value
    * */
    fun getDayOfWeek(dayOfWeek: Int): String {
        // 요일을 담을 String 변수
        var dayOfWeekText = ""

        when (dayOfWeek) {
            1 -> dayOfWeekText = "일"
            2 -> dayOfWeekText = "월"
            3 -> dayOfWeekText = "화"
            4 -> dayOfWeekText = "수"
            5 -> dayOfWeekText = "목"
            6 -> dayOfWeekText = "금"
            7 -> dayOfWeekText = "토"
        }

        return dayOfWeekText
    }


    /*
    *  특정 날짜에 대하여 요일을 구함 (일 ~ 토)
    *  @param date : 입력받을 날짜의 String 형태 (ex. 2022-02-22)
    * */
    @SuppressLint("SimpleDateFormat")
    fun getDayOfWeek(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(date)!!
        val calendar = Calendar.getInstance()

        calendar.time = parsedDate // 캘린더 객체에 Date 지정
        return getDayOfWeek(calendar[DAY_OF_WEEK]) // Calendar의 요일 숫자를 요일 텍스트로 반환
    }

    /*
    *  @param date : 변환할 Date 객체
    *  @return 2022. 02. 17 목  오전 12 : 50
    * */
    @SuppressLint("SimpleDateFormat")
    fun getFullDate(date: Date?): String? {
        return if(date != null) {
            val dateFormat = SimpleDateFormat("yyyy. MM. dd. E  a HH : mm")
            dateFormat.format(date.time)
        } else {
            null
        }
    }
}