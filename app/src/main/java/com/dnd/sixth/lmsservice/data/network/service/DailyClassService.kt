package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import retrofit2.Call
import retrofit2.http.GET

interface DailyClassService {

    //Subject id를 넘겨 받고 해당 id의 모든 dailyclass를 요청 이 없어서 모든 dailyclass 요청
    @GET("/dailyclass/all")
    fun getAllDailyclass() : Call<List<DailyClassGetModel>>

    //하나의 데일리클래스 객체만 받음.
    @GET("/dailyclass")
    fun getDailyclass() : Call<DailyClassGetModel>


}