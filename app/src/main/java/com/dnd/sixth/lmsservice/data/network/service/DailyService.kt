package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import retrofit2.Call
import retrofit2.http.*

interface DailyService {
    @POST("/dailyclass")
    fun createDaily(
        @Body dailyModel: DailyModel
    ): Call<DailyModel>

    @PUT("/dailyclass")
    fun updateDaily(
        @Body dailyModel: DailyModel
    ): Call<DailyModel>

    @DELETE("/dailyclass")
    fun deleteDaily(
        @Query("id") uid: Int
    ): Call<DailyModel>

    @GET("/dailyclass")
    fun getDailyClassList(
        @Query("id") uid: Int
    ): Call<List<DailyModel>>

    @GET("/dailyclass")
    fun getDaily(
        @Query("id") dailyId: Int,
        @Query("number") subjectId: Int
    ): Call<DailyModel>

    @GET("/dailyclass/all")
    fun getAllDailyList(): Call<List<DailyModel>>
}