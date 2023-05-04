package com.dnd.sixth.lmsservice.presentation.main

import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    var lastPressedTime = 0L
    private val backPressTerm = 2000

    // {마지막에 누른 시간 + (backPressTerm : 2초)}가 지난 후에 BackKey를 누르면 종료 경고 Toast 출력
    fun canFinish(): Boolean {
        val newlyPressedTime = System.currentTimeMillis()

        return if(lastPressedTime + backPressTerm < newlyPressedTime) {
            lastPressedTime = newlyPressedTime
            false
        } else {
            true
        }
    }
}