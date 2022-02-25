package com.dnd.sixth.lmsservice.data.model.user

import com.google.gson.annotations.SerializedName

data class UserPutDTO(
    @SerializedName("id")
    var id: Int,
    @SerializedName("user_nm")
    var userName: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("phone_num")
    var phoneNumber: String? = null,
    @SerializedName("parent_phone_num")
    var contactTime: String? = null,
    @SerializedName("contact_time")
    var parentPhoneNumber: String? = null,
)
