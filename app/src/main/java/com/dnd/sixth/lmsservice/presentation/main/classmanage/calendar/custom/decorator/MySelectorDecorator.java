package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.decorator;

import com.dnd.sixth.lmsservice.App;
import com.dnd.sixth.lmsservice.R;
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarViewModel;
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.span.CenteredDotSpan;
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

/**
 * Use a custom selector
 */
public class MySelectorDecorator implements DayViewDecorator {

    CalendarViewModel viewModel;
    @IdRes int drawableResId;

    public MySelectorDecorator(int drawableResId, CalendarViewModel viewModel) {
        this.drawableResId = drawableResId;
        this.viewModel = viewModel;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(App.Companion.getContext().getDrawable(drawableResId)); // 가져온 Selector 이미지 등록
        view.addSpan(new ForegroundColorSpan(Color.BLACK)); // 날짜 텍스트 색상 검정색
    }
}