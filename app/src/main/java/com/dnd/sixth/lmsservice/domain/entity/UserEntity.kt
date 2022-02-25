package com.dnd.sixth.lmsservice.domain.entity

data class UserEntity(
    var id : Int,
    var email: String,
    var userName: String,
    var password: String?,
    var role: Number,
    var phoneNumber: Number,
    var parentPhoneNum : String,
    var profileUrl: String,
    var contactTime: String? = null
)