package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.response.UserCreateDto
import com.dnd.sixth.lmsservice.data.response.User
import com.dnd.sixth.lmsservice.data.response.UserListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignService {

    //로그인
    //@POST("/auth/login")
    //fun login() :

    //화원가입
    @POST("/user")
    fun signUp(@Body post : UserCreateDto) : Call<User>

    //임시로 사용
    //유저 정보 받아오기 (원래 전체 유저 받아오는 요청)
    @GET("/user/all")
    fun getUserInfo() : Call<UserListResponse>
}