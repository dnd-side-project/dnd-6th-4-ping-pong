package com.dnd.sixth.lmsservice.presentation.utility

class DateConverter {

    fun getDayOfWeek(dayOfWeek: Int): String {
        // 요일을 담을 String 변수
        var dayOfWeekText = ""

        when(dayOfWeek) {
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
}