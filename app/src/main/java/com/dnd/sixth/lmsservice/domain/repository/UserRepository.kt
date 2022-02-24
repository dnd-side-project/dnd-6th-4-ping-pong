package com.dnd.sixth.lmsservice.domain.repository

interface UserRepository {
    suspend fun changeUserName(uid: Number, newName: String): Int
    suspend fun changePassword(uid: Number, newPassword: String): Int
}