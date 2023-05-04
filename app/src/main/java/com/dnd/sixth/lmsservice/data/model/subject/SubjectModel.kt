package com.dnd.sixth.lmsservice.data.model.subject

import com.google.gson.annotations.SerializedName

data class SubjectModel(
    @SerializedName("id")
    val id: Number?,
    @SerializedName("subject_nm")
    val subjectName: String,
    @SerializedName("monthly_cnt")
    val monthlyCnt: Number,
    @SerializedName("class_time")
    val classTime: String,
    @SerializedName("teacher_id")
    val teacherId: Number?,
    @SerializedName("student_id")
    val studentId: Number? = null,
    @SerializedName("color")
    val color: Number,
    @SerializedName("class_days")
    val classDayBit: String?
)
