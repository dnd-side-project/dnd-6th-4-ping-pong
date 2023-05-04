package com.dnd.sixth.lmsservice.presentation.utility

import android.content.res.Resources

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