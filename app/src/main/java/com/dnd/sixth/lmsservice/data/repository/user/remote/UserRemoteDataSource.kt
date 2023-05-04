package com.dnd.sixth.lmsservice.data.repository.user.remote

import android.net.Uri
import com.dnd.sixth.lmsservice.data.response.UserModel

interface UserRemoteDataSource {
    suspend fun changeUserName(uid: Number, newName: String): Int
    suspend fun changePassword(uid: Number, newPassword: String): Int
    suspend fun saveContactTime(uid: Number, contactTime: String): Int
    suspend fun saveMyNumber(uid: Number, myNumber: String): Int
    suspend fun saveParentNumber(uid: Number, parentNumber: String): Int
    suspend fun saveRemoteProfileUri(uid: Number, profileUri: Uri): Uri?
    suspend fun getUser(email: String): UserModel
    suspend fun getUser(uid: Int): UserModel
}