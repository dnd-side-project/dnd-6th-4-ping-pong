package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.response.UserModel
import com.dnd.sixth.lmsservice.domain.entity.UserEntity

fun UserModel.toEntity(): UserEntity {
    return UserEntity(
        this.id,
        this.email,
        this.userName,
        this.password,
        this.role,
        this.phoneNumber,
        this.parentPhoneNum,
        this.profile_url,
        this.contactTime
    )
}

fun UserEntity.toModel(): UserModel {
    return UserModel(
        this.id,
        this.email,
        this.userName,
        this.password,
        this.role,
        this.phoneNumber,
        this.parentPhoneNum,
        this.profileUrl,
        this.contactTime
    )
}

