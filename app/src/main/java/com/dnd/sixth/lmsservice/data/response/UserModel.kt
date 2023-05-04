package com.dnd.sixth.lmsservice.data.response

import com.dnd.sixth.lmsservice.data.model.SignUpResponseEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class UserModel(
    @SerializedName("id")
    var id : Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("user_nm")
    var userName: String,
    @SerializedName("password")
    var password: String?,
    @SerializedName("role")
    var role: Number,
    @SerializedName("phone_num")
    var phoneNumber: Number,
    @SerializedName("parent_phone_num")
    var parentPhoneNum : String,
    @SerializedName("profile_url")
    var profile_url: String,
    @SerializedName("contact_time")
    var contactTime: String? = null
){
    //함수를 추가하여
    //Entity로 바꿀 수 있는 형태로 만들어줌.
    fun toSignUpResponseEntity(): SignUpResponseEntity =
        SignUpResponseEntity(
            id = id.toLong(),
            parent_phone_num = null,
            profile_url = profile_url
        )
}
