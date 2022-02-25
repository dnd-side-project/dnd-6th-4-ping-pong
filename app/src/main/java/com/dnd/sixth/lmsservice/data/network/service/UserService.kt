package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.response.UserModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    // 유저 정보를 반환합니다.
    @PUT("/user/{email}")
    fun getUser(@Path("email") email: String): Call<UserModel>

    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/name")
    fun updateUserName(@Query("id") uid: Number, @Query("user_nm") userName: String): Call<Int>

    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/password")
    fun updatePassword(@Query("id") uid: Number, @Query("password") password: String): Call<Int>

    // 유저 연락 가능 시간대를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/contact-time")
    fun updateContactTime(@Query("id") uid: Number, @Query("contact_time") contact_time: String): Call<Int>

    // 본인 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/phone-num")
    fun updateMyNumber(@Query("id") uid: Number, @Query("phone_num") phoneNumber: String): Call<Int>

    // 학부모 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/parent-phone-num")
    fun updateParentNumber(@Query("id") uid: Number, @Query("parent_phone_num") parentParentNumber: String): Call<Int>

    // 프로필을 변경하고, 변경된 프로필 Uri를 반환합니다.
    @PUT("/user/profile")
    fun updateProfileUri(@Query("id") uid: Number, @Part profilePart: MultipartBody.Part): Call<String>
}