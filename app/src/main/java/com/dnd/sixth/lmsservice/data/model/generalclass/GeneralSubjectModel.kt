package com.dnd.sixth.lmsservice.data.model.generalclass

import com.google.gson.annotations.SerializedName

/*
*  Daily, Subject 데이터를 그룹핑한 Model
* */
data class GeneralSubjectModel(
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
    val classDays: String?, //수업 요일 비트형태 'ex) 0b1000011'

    val studentName: String,
    val classId: Number, // 수업 Uid
    val userId: Number, // 학생 Uid
    val profileUri: String? = null, // 프로필 Uri
    val isFeedbackChange: Boolean = false // 피드백 업데이트 유무
)