package com.dnd.sixth.lmsservice.data.repository.user

import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSource
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {
    override suspend fun changeUserName(uid: Number, newName: String): Int {
        return userRemoteDataSource.changeUserName(uid, newName)
    }

    override suspend fun changePassword(uid: Number, newPassword: String): Int {
        return userRemoteDataSource.changePassword(uid, newPassword)
    }
}