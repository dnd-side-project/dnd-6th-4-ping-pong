package com.dnd.sixth.lmsservice.data.repository.user

import android.net.Uri
import com.dnd.sixth.lmsservice.data.repository.user.local.UserLocalDataSource
import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSource
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    /* Remote */
    override suspend fun changeUserName(uid: Number, newName: String): Int =
        userRemoteDataSource.changeUserName(uid, newName)


    override suspend fun changePassword(uid: Number, newPassword: String): Int =
        userRemoteDataSource.changePassword(uid, newPassword)


    override suspend fun saveRemoteContactTime(uid: Number, contactTime: String): Int =
        userRemoteDataSource.saveContactTime(uid, contactTime)


    override suspend fun saveRemoteMyNumber(uid: Number, myNumber: String): Int =
        userRemoteDataSource.saveMyNumber(uid, myNumber)


    override suspend fun saveRemoteParentNumber(uid: Number, parentNumber: String): Int =
         userRemoteDataSource.saveParentNumber(uid, parentNumber)


    override suspend fun saveLocalProfileUri(uid: Number, profileUri: Uri): Uri? =
        userRemoteDataSource.saveRemoteProfileUri(uid, profileUri)



    /* Local */
    override suspend fun saveLocalContactTime(contactTime: String) {
        userLocalDataSource.saveContactTime(contactTime)
    }

    override suspend fun getLocalContactTime(): String? =
        userLocalDataSource.getContactTime()


    override suspend fun saveLocalMyNumber(myNumber: String) {
        userLocalDataSource.saveMyNumber(myNumber)
    }

    override suspend fun getLocalMyNumber(): String? =
        userLocalDataSource.getMyNumber()


    override suspend fun saveLocalParentNumber(parentNumber: String) {
        userLocalDataSource.saveParentNumber(parentNumber)
    }

    override suspend fun getLocalParentNumber(): String? =
        userLocalDataSource.getParentNumber()


    override fun saveLocalProfileUri(profileUri: Uri) {
        userLocalDataSource.saveLocalProfileUri(profileUri)
    }

    override fun getLocalProfileUri(): Uri =
        userLocalDataSource.getLocalProfileUri()

}