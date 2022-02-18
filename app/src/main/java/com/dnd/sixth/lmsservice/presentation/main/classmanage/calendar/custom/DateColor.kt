package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom

import androidx.annotation.IdRes
import com.dnd.sixth.lmsservice.R

enum class DateColor(@IdRes private val resId: Int) {
    RED(R.color.calendarRed),
    ORANGE(R.color.calendarOrange),
    YELLOW(R.color.calendarYellow),
    GREEN(R.color.calendarGreen),
    BLUE(R.color.calendarBlue),
    DARK_BLUE(R.color.calendarDarkBlue),
    PURPLE(R.color.calendarPurple);

    fun getColorResId(): Int = this.resId
}