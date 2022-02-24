package com.dnd.sixth.lmsservice.data.repository.user

import com.dnd.sixth.lmsservice.data.repository.user.local.UserLocalDataSource
import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSource
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun changeUserName(uid: Number, newName: String): Int {
        return userRemoteDataSource.changeUserName(uid, newName)
    }

    override suspend fun changePassword(uid: Number, newPassword: String): Int {
        return userRemoteDataSource.changePassword(uid, newPassword)
    }

    override suspend fun saveRemoteContactTime(uid: Number, contactTime: String): Int {
        return userRemoteDataSource.saveContactTime(uid, contactTime)
    }

    override suspend fun saveLocalContactTime(contactTime: String) {
        userLocalDataSource.saveContactTime(contactTime)
    }

    override suspend fun getLocalContactTime(): String? {
        return userLocalDataSource.getContactTime()
    }
}