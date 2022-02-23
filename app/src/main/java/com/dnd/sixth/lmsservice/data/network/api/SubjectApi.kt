package com.dnd.sixth.lmsservice.data.network.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface SubjectApi {
    @POST("/subject")
    fun makeSubject(
        @Query("subject_nm") subjectName: String,
        @Query("monthly_cnt") monthlyCnt: Number,
        @Query("class_time") classTime: String,
        @Query("class_day") classDay: String,
        @Query("teacher_id") teacherId: Number,
        @Query("color") color: Number,
        @Query("class_days") classDays: Number
    ): Response<Boolean>

}