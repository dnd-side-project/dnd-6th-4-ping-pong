package com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.decorator

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.custom.span.CenteredDotSpan
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

/*
* 수업이 있는 날짜를 꾸며주는 데코레이터
* */

class ScheduleDecorator(
    private val color: Int, private val dates: List<CalendarDay>
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        // 해당 날짜 텍스트는 흰색
        view?.addSpan(ForegroundColorSpan(Color.WHITE))
        // 날짜 중앙에 점 생성
        view?.addSpan(
            CenteredDotSpan(
                UnitConverter.convertDPtoPX(App.context!!, 18).toFloat(),
                color
            )
        )
    }
}

