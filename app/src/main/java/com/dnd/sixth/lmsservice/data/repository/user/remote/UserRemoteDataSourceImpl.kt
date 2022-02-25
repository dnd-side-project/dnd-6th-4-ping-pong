package com.dnd.sixth.lmsservice.data.repository.user.remote

import com.dnd.sixth.lmsservice.data.network.api.UserApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSourceImpl(private val userApi: UserApi) : UserRemoteDataSource {
    override suspend fun changeUserName(uid: Number, newName: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.changeUserName(uid, newName)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                }
            })
        }


    override suspend fun changePassword(uid: Number, newPassword: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.changePassword(uid, newPassword)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                }
            })
        }


    override suspend fun saveContactTime(uid: Number, contactTime: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.saveContactTime(uid, contactTime)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("SaveFailException")))
                }
            })
        }

    override suspend fun saveMyNumber(uid: Number, myNumber: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.saveMyNumber(uid, myNumber)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("SaveFailException")))
                }
            })
        }

    override suspend fun saveParentNumber(uid: Number, parentNumber: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.saveParentNumber(uid, parentNumber)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("SaveFailException")))
                }
            })
        }
}