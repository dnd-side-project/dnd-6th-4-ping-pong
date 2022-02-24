package com.dnd.sixth.lmsservice.data.repository.user.remote

interface UserRemoteDataSource {
    suspend fun changeUserName(uid: Number, changeName: String): Int
}