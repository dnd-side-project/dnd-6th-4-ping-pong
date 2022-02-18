package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.span

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R

class CenteredDotSpan : LineBackgroundSpan {

    private val DEFAULT_RADIUS = 3F

    private val radius: Float
    private var color: Int

    constructor() {
        this.radius = DEFAULT_RADIUS
        this.color = 0
    }

    constructor(radius: Float, color: Int) {
        this.radius = radius
        this.color = color
    }


    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        val oldColor = paint.color
        if (color != 0) {
            paint.color = color
        }

        // 중앙에 반지름 Radius만큼의 원을 그린다
        canvas.drawCircle(
            ((left + right) / 2).toFloat(),
            ((bottom + top) / 2).toFloat(),
            radius,
            paint
        )

        paint.color = oldColor
    }
}