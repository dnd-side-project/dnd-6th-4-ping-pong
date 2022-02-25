package com.dnd.sixth.lmsservice.data.repository.user.remote

import android.net.Uri
import android.util.Log
import com.dnd.sixth.lmsservice.data.model.user.UserPutDTO
import com.dnd.sixth.lmsservice.data.network.api.UserApi
import com.dnd.sixth.lmsservice.data.response.UserModel
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserRemoteDataSourceImpl(private val userApi: UserApi) : UserRemoteDataSource {
    override suspend fun changeUserName(uid: Number, newName: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.updateUserName(
                UserPutDTO(
                    id = uid.toInt(),
                    userName = newName
                )
            )
            requestCall.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        Log.d("Ddddd", response.message())
                        cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }


    override suspend fun changePassword(uid: Number, newPassword: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.updatePassword(UserPutDTO(
                id = uid.toInt(),
                password = newPassword
            ))
            requestCall.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("ChangeFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }


    override suspend fun saveContactTime(uid: Number, contactTime: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.updateContactTime(UserPutDTO(
                id = uid.toInt(),
                contactTime = contactTime
            ))
            requestCall.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        Log.d("ddddd", response.code().toString())
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }

    override suspend fun saveMyNumber(uid: Number, myNumber: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.updateMyNumber(UserPutDTO(
                id = uid.toInt(),
                phoneNumber = myNumber
            ))
            requestCall.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }

    override suspend fun saveParentNumber(uid: Number, parentNumber: String): Int =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.updateParentNumber(UserPutDTO(
                id = uid.toInt(),
                parentPhoneNumber = parentNumber
            ))
            requestCall.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val changeCount = response.body() as Int
                        cont.resumeWith(Result.success(changeCount))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }


    override suspend fun saveRemoteProfileUri(uid: Number, profileUri: Uri): Uri? =
        suspendCancellableCoroutine { cont ->
            val multiPartQuery = "img"
            val fileName = "profile$uid"
            val fileBody = File(profileUri.toString()).asRequestBody("image/*".toMediaTypeOrNull())
            val fileMultiPart = MultipartBody.Part.createFormData(
                multiPartQuery,
                fileName,
                fileBody
            )
            val requestCall = userApi.api.updateProfileUri(uid, fileMultiPart)
            requestCall.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val profileUri = Uri.parse(response.body() as String)
                        cont.resumeWith(Result.success(profileUri))
                    } else {
                        cont.resumeWith(Result.success(null))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }

    override suspend fun getUser(email: String): UserModel =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.getUserByEmail(email)
            requestCall.enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if (response.isSuccessful) {
                        val userModel = response.body() as UserModel
                        cont.resumeWith(Result.success(userModel))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }

    override suspend fun getUser(uid: Int): UserModel =
        suspendCancellableCoroutine { cont ->
            val requestCall = userApi.api.getUserByUid(uid)
            requestCall.enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if (response.isSuccessful) {
                        val userModel = response.body() as UserModel
                        cont.resumeWith(Result.success(userModel))
                    } else {
                        cont.resumeWith(Result.failure(Exception("SaveFailException")))
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    cont.resumeWith(Result.failure(Exception("UpdateFailException")))
                }
            })
        }

}