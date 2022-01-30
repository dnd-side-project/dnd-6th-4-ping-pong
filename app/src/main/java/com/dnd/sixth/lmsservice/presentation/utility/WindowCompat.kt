package com.dnd.sixth.lmsservice.presentation.utility

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.hardware.display.DisplayManager
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

object WindowCompat {

    // 스크린 높이 구하기
    fun getWindowHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    // 스크린 너비 구하기
    fun getWindowWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}