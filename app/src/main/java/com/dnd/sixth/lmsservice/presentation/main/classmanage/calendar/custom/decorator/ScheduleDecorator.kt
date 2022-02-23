package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan


/*
*   수업이 있는 날짜를 점으로 꾸며주는 데코레이터
*   @Params dates : 점(DOt)을 찍을 수업 날짜 리스트
*   @Params dateColor : 찍을 점의 색상을 가지고 있는 Enum Class
* */

class ScheduleDecorator(
    private val dates: List<CalendarDay>,
    private val dateColor: DateColor
) : DayViewDecorator {


    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    @SuppressLint("ResourceType")
    override fun decorate(view: DayViewFacade?) {
        // 해당 날짜에 점(Dot)을 표시한다.
        view?.addSpan(DotSpan(8F, ContextCompat.getColor(App.instance.context, dateColor.getTextColorResId())))
    }
}

