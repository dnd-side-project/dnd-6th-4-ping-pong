package com.dnd.sixth.lmsservice.presentation.utility

import android.content.Context
import kotlin.math.roundToInt

object UnitConverter {
    fun convertDPtoPX(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return ((dp.toFloat()) * density).roundToInt()
    }

    fun convertPXtoDP(context: Context, px: Int): Int {
        val density = context.resources.displayMetrics.density
        return ((px.toFloat()) / density).roundToInt()
    }
}