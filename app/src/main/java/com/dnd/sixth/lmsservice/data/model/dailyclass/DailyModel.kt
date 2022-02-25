package com.dnd.sixth.lmsservice.data.model.dailyclass

import com.google.gson.annotations.SerializedName

data class DailyModel(
    @SerializedName("id")
    val id: Number? = null,
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
    val dailyFeedback: String? = null,
    @SerializedName("daily_comment")
    val dailyComment: String? = null,
    @SerializedName("homework")
    val homework: String? = null,
    @SerializedName("change_feedback")
    val isChangeFeedback: Boolean = false,
    @SerializedName("change_start_time")
    val changeStartTime: String? = null,
    @SerializedName("difficulty")
    val difficulty: Int? = null
)