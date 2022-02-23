package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.response.UserCreateDto
import com.dnd.sixth.lmsservice.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignService {

    //로그인
    //@POST("/auth/login")
    //fun login() :

    //화원가입
    @POST("/user")
    fun signUp(@Body post : UserCreateDto) : Call<UserResponse>

    //로그인 시점에 저장할 유저의 정보 저장
    //유저 정보 받아오기 (원래 전체 유저 받아오는 요청)
    @GET("/user/{email}")
    fun getUserInfo(@Path("email") email : String) : Call<UserResponse>
}