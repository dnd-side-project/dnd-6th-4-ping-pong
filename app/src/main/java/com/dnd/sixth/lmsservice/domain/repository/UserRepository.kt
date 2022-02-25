package com.dnd.sixth.lmsservice.domain.repository

interface UserRepository {
    /*
    *  Remote
    * */
    suspend fun changeUserName(uid: Number, newName: String): Int
    suspend fun changePassword(uid: Number, newPassword: String): Int
    suspend fun saveRemoteContactTime(uid: Number, contactTime: String): Int
    suspend fun saveRemoteMyNumber(uid: Number, myNumber: String): Int
    suspend fun saveRemoteParentNumber(uid: Number, parentNumber: String): Int

    /*
    *  Local
    * */
    suspend fun saveLocalContactTime(contactTime: String)
    suspend fun getLocalContactTime(): String?
    suspend fun saveLocalMyNumber(myNumber: String)
    suspend fun getLocalMyNumber(): String?
    suspend fun saveLocalParentNumber(parentNumber: String)
    suspend fun getLocalParentNumber(): String?
}