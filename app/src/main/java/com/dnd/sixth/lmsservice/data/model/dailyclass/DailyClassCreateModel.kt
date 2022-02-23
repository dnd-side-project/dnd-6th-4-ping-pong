package com.dnd.sixth.lmsservice.data.model.dailyclass

import com.google.gson.annotations.SerializedName

data class DailyClassCreateModel(
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("class_order")
    val classOrder: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("memo")
    val memo: String,
    @SerializedName("noty")
    val noty: String,
    @SerializedName("daily_feedback")
    val dailyFeedback: String,
    @SerializedName("daily_comment")
    val dailyComment: String,
    @SerializedName("homework")
    val homework: String,
    @SerializedName("change_feedback")
    val isChangeFeedback: Boolean,
    @SerializedName("change_start_time")
    val changeStartTime: String,
)