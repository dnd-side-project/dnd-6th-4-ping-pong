package com.dnd.sixth.lmsservice.data.repository.user.remote

interface UserRemoteDataSource {
    suspend fun changeUserName(uid: Number, newName: String): Int
    suspend fun changePassword(uid: Number, newPassword: String): Int
    suspend fun saveContactTime(uid: Number, contactTime: String): Int
    suspend fun saveMyNumber(uid: Number, myNumber: String): Int
    suspend fun saveParentNumber(uid: Number, parentNumber: String): Int
}