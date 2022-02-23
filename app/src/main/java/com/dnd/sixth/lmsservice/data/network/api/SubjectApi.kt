package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.SignService
import com.dnd.sixth.lmsservice.data.network.service.SubjectService
import com.google.gson.annotations.SerializedName

class SubjectApi : BaseApi() {
    var api = retrofit.create(SubjectService::class.java)

}

/*
data class asd(
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
    val classDays: String, // 수업 요일 비트형태 'ex) 1000011'
    val classId: Number, // 수업 Uid
    val userId: Number, // 학생 Uid
    val profileUri: String, // 프로필 Uri
    val isFeedbackChange: Boolean // 피드백 업데이트 유무
)*/
