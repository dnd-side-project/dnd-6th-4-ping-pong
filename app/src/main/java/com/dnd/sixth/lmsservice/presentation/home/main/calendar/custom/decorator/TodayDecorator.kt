package com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.decorator

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.span.CenteredStrokeDotSpan
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

/*
* 오늘 날짜를 꾸며주는 데코레이터
* */

class TodayDecorator(val res: Resources) : DayViewDecorator {

    val date = CalendarDay.today()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date) ?: false
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(StyleSpan(Typeface.BOLD)) // 날짜 텍스트 Bold
        view?.addSpan(ForegroundColorSpan(Color.WHITE)) // 날짜 텍스트 색상 흰색
        view?.addSpan(RelativeSizeSpan(1.1f)) // 상대적인 크기 1.1배
        view?.addSpan(
            CenteredStrokeDotSpan(
                UnitConverter.convertDPtoPX(App.context!!, 18).toFloat(), Color.WHITE
            )
        ) // 흰색 테두리
    }
}