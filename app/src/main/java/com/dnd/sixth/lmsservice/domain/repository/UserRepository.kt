package com.dnd.sixth.lmsservice.domain.repository

interface UserRepository {
    suspend fun changeUserName(uid: Number, newName: String): Int
    suspend fun changePassword(uid: Number, newPassword: String): Int
    suspend fun saveRemoteContactTime(uid: Number, contactTime: String): Int
    suspend fun saveLocalContactTime(contactTime: String)
    suspend fun getLocalContactTime(): String?
}