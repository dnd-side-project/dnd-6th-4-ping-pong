package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.user.UserPutDTO
import com.dnd.sixth.lmsservice.data.response.UserModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    // 이메일에 해당하는 유저 정보를 반환합니다.
    @GET("/user/{email}")
    fun getUserByEmail(@Path("email") email: String): Call<UserModel>

    // Uid에 해당하는 유저 정보를 반환합니다.
    @GET("/user/{id}")
    fun getUserByUid(@Path("id") uid: Int): Call<UserModel>

    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("user/name")
    fun updateUserName(@Body userPutDTO: UserPutDTO): Call<Int>

    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/password")
    fun updatePassword(@Body userPutDTO: UserPutDTO): Call<Int>

    // 유저 연락 가능 시간대를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/contact-time")
    fun updateContactTime(@Body userPutDTO: UserPutDTO): Call<Int>

    // 본인 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/phone-num")
    fun updateMyNumber(@Body userPutDTO: UserPutDTO): Call<Int>

    // 학부모 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/parent-phone-number")
    fun updateParentNumber(@Body userPutDTO: UserPutDTO): Call<Int>

    // 프로필을 변경하고, 변경된 프로필 Uri를 반환합니다.
    @PUT("/user/profile")
    fun updateProfileUri(@Query("id") uid: Number, @Part profilePart: MultipartBody.Part): Call<String>
}