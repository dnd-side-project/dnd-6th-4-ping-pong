package com.dnd.sixth.lmsservice.domain.repository

interface UserRepository {
    suspend fun changeUserName(uid: Number, changeName: String): Int
}