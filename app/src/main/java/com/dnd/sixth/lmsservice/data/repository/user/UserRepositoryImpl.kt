package com.dnd.sixth.lmsservice.data.repository.user

import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSource
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {
    override suspend fun changeUserName(uid: Number, changeName: String): Int {
        return userRemoteDataSource.changeUserName(uid, changeName)
    }
}