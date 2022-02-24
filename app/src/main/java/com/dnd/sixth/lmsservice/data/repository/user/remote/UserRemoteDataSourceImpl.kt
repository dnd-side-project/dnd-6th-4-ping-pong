package com.dnd.sixth.lmsservice.data.repository.user.remote

import android.util.Log
import com.dnd.sixth.lmsservice.data.network.api.UserApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSourceImpl(private val userApi: UserApi) : UserRemoteDataSource {
    override suspend fun changeUserName(uid: Number, changeName: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.changeUserName(uid, changeName)
            requestCall.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        Log.d("dddd", response.message().toString())
                        Log.d("dddd", response.code().toString())
                        cont.resumeWith(Result.failure(Exception("Change User Name Fail Exception")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("Change User Name Fail Exception")))
                }
            })
        }
}