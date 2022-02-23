package com.dnd.sixth.lmsservice.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
*  Daily, Subject 데이터를 그룹핑한 Entity
* */
data class GeneralSubjectEntity(
    @SerializedName("subject_nm")
    val subjectName: String,
    @SerializedName("monthly_cnt")
    val monthlyCnt: Number,
    @SerializedName("class_time")
    val classTime: String,
    @SerializedName("class_day")
    val classDay: String,
    @SerializedName("teacher_id")
    val teacherId: Number,
    @SerializedName("color")
    val color: Number,

    val teacherName: String,
    val studentName: String,
    val classDayBit: String, // 수업 요일 비트형태 'ex) 0b1000011'
    val classId: Number, // 수업 Uid
    val userId: Number, // 학생 Uid
    val profileUri: String, // 프로필 Uri
    val isFeedbackChange: Boolean // 피드백 업데이트 유무
): Serializable