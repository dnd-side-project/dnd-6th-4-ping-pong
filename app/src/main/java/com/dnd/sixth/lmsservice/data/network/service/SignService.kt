package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.response.UserCreateDto
import com.dnd.sixth.lmsservice.data.response.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {

    //화원가입
    @POST("/user")
    fun signUp(@Body post : UserCreateDto) : Call<User>
}