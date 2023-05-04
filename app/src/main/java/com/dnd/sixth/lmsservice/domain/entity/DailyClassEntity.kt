package com.dnd.sixth.lmsservice.domain.entity

import java.io.Serializable

data class DailyClassEntity(

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
    val classDays: Number,
    val difficulty : Int,
    val dailyClassId : Number
): Serializable
