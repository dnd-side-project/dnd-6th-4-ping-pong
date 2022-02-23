package com.dnd.sixth.lmsservice.data.response

import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.google.gson.annotations.SerializedName


//회원가입 요청 바디

data class UserCreateDto(

    @SerializedName("email") val email : String,
    @SerializedName("user_nm") val userNM : String,
    @SerializedName("password") val password : String,
    @SerializedName("role") val role : Int,
    @SerializedName("phone_num") var phoneNum : String,
    @SerializedName("parent_phone_num") val parentPhoneNum : String?
)
