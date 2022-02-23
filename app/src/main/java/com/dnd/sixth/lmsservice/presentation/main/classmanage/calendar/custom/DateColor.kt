package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom

import androidx.annotation.IdRes
import com.dnd.sixth.lmsservice.R

enum class DateColor(
    @IdRes private val textColorResId: Int,
    @IdRes private val bgColorResId: Int
) {
    RED(R.color.calendarTextRed, R.color.calendarRed),
    ORANGE(R.color.calendarTextOrange, R.color.calendarOrange),
    YELLOW(R.color.calendarTextYellow, R.color.calendarYellow),
    GREEN(R.color.calendarTextGreen, R.color.calendarGreen),
    BLUE(R.color.calendarTextBlue, R.color.calendarBlue),
    DARK_BLUE(R.color.calendarTextDarkBlue, R.color.calendarDarkBlue),
    PURPLE(R.color.calendarTextPurple, R.color.calendarPurple);

    fun getTextColorResId(): Int = this.textColorResId
    fun getBgColorResId(): Int = this.bgColorResId
}