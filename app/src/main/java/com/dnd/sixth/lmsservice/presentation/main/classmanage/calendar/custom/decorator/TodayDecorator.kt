package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.span.CenteredDotSpan
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

/*
* 오늘 날짜를 꾸며주는 데코레이터
* */

class TodayDecorator(@IdRes val color: Int) : DayViewDecorator {

    val date = CalendarDay.today()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date) ?: false
    }

    @SuppressLint("ResourceType")
    override fun decorate(view: DayViewFacade?) {
        view?.run {
            // addSpan(StyleSpan(Typeface.BOLD)) // 날짜 텍스트 Bold
            addSpan(ForegroundColorSpan(Color.WHITE)) // 날짜 텍스트 색상 흰색
            addSpan(RelativeSizeSpan(1.0f)) // 상대적인 크기 1.0배
            addSpan(
                CenteredDotSpan(
                    UnitConverter.convertDPtoPX(App.instance.context, 18).toFloat(),
                    ContextCompat.getColor(App.instance.context, color)
                )
            ) // 흰색 테두리
        }
    }
}