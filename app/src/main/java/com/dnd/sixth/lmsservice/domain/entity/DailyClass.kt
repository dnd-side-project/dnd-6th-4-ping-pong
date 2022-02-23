package com.dnd.sixth.lmsservice.domain.entity

import java.io.Serializable

data class DailyClass(
    val subjectId: Number,
    val classOrder: Number,
    val startTime: String,
    val place: String,
    val chapter: String,
    val memo: String,
    val noty: String,
    val dailyFeedback: String,
    val dailyComment: String,
    val homework: String,
    val isChangeFeedback: Boolean,
    val changeStartTime: String,
    val classDays: Number
): Serializable