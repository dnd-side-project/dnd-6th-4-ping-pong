package com.dnd.sixth.lmsservice.domain.entity

import java.io.Serializable

data class DailyEntity(
    val id: Number? = null,
    val subjectId: Number,
    val classOrder: Number, // 회차
    val startTime: String, // "수업 시작 일시) ex. 2022-02-24 03:50"
    val place: String,
    val chapter: String,
    val memo: String,
    val noty: String,
    val dailyFeedback: String? = null,
    val dailyComment: String? = null,
    val homework: String? = null,
    val isChangeFeedback: Boolean = false,
    val changeStartTime: String? = null,
    val difficulty: Int? = null
): Serializable {
    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd HH-mm"
    }
}