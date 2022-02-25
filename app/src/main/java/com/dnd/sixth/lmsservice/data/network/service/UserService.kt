package com.dnd.sixth.lmsservice.data.network.service

import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {
    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/name")
    fun changeUserName(@Query("id") uid: Number, @Query("user_nm") userName: String): Call<Int>

    // 유저 이름을 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/password")
    fun changePassword(@Query("id") uid: Number, @Query("password") password: String): Call<Int>

    // 유저 연락 가능 시간대를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/contact-time")
    fun saveContactTime(@Query("id") uid: Number, @Query("contact_time") contact_time: String): Call<Int>

    // 본인 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/phone-num")
    fun saveMyNumber(@Query("id") uid: Number, @Query("phone_num") phoneNumber: String): Call<Int>

    // 학부모 휴대폰 번호를 변경하고, 변경한 데이터의 숫자를 반환받습니다.
    @PUT("/user/parent-phone-num")
    fun saveParentNumber(@Query("id") uid: Number, @Query("parent_phone_num") parentParentNumber: String): Call<Int>
}