package com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import com.dnd.sixth.lmsservice.data.network.api.DailyApi
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resumeWithException

class DailyClassRemoteDataSourceImpl(private val dailyApi: DailyApi) : DailyClassRemoteDataSource {

    private val TAG = "DailyClassRemoteDataSourceImpl"

    override suspend fun getDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyId: Int,
        subjectId: Int
    ): DailyModel = suspendCancellableCoroutine { cont ->
        val requestCall = dailyApi.api.getDaily(dailyId, subjectId)

        requestCall.enqueue(object : Callback<DailyModel> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<DailyModel>, response: Response<DailyModel>) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    val dailyModel = response.body() as DailyModel
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, dailyModel.toString())
                    }

                    // 코루틴 재게
                    cont.resumeWith(Result.success(dailyModel))
                } else { // 서버로부터 에러 반환
                    Log.e(TAG, response.message().toString())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<DailyModel>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }
        })
    }

    override suspend fun createDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyModel: DailyModel
    ): DailyModel? = suspendCancellableCoroutine { cont ->
        val requestCall = dailyApi.api.createDaily(dailyModel)

        requestCall.enqueue(object : Callback<DailyModel> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<DailyModel>, response: Response<DailyModel>) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    val dailyModel = response.body() as DailyModel
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, dailyModel.toString())
                    }

                    // 코루틴 재게
                    cont.resumeWith(Result.success(dailyModel))
                } else { // 서버로부터 에러 반환
                    Log.e(TAG, response.message().toString())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<DailyModel>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }
        })
    }


    override suspend fun getDailyClassList(
        remoteErrorEmitter: RemoteErrorEmitter,
        userId: Int
    ): List<DailyModel>? = suspendCancellableCoroutine { cont ->
        val requestCall = dailyApi.api.getDailyClassList(userId)

        requestCall.enqueue(object : Callback<List<DailyModel>> {
            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<List<DailyModel>>,
                response: Response<List<DailyModel>>
            ) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    val dailyModel = response.body() as List<DailyModel>
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, dailyModel.toString())
                    }

                    cont.resumeWith(Result.success(dailyModel)) // 코루틴 재게
                }
                // 서버로부터 에러 반환
                else {
                    Log.e(TAG, response.code().toString())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<List<DailyModel>>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }
        })
    }

    override suspend fun deleteDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyId: Int
    ): DailyModel? = suspendCancellableCoroutine { cont ->
        val requestCall = dailyApi.api.deleteDaily(dailyId)

        requestCall.enqueue(object : Callback<DailyModel> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<DailyModel>, response: Response<DailyModel>) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    val dailyModel = response.body() as DailyModel
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, dailyModel.toString())
                    }

                    cont.resumeWith(Result.success(dailyModel)) // 코루틴 재게
                }
                // 서버로부터 에러 반환
                else {
                    Log.e(TAG, response.code().toString())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<DailyModel>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }
        })
    }

    override suspend fun updateDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyModel: DailyModel
    ): DailyModel? = suspendCancellableCoroutine { cont ->
        val requestCall = dailyApi.api.updateDaily(dailyModel)

        requestCall.enqueue(object : Callback<DailyModel> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<DailyModel>, response: Response<DailyModel>) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    val dailyModel = response.body() as DailyModel
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, dailyModel.toString())
                    }

                    cont.resumeWith(Result.success(dailyModel)) // 코루틴 재게
                }
                // 서버로부터 에러 반환
                else {
                    Log.e(TAG, response.code().toString())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<DailyModel>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }
        })
    }

    override suspend fun getAllDailyClass(remoteErrorEmitter: RemoteErrorEmitter): List<DailyModel> =
        suspendCancellableCoroutine { cont ->
            val requestCall = dailyApi.api.getAllDailyList()

            requestCall.enqueue(object : Callback<List<DailyModel>> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<List<DailyModel>>,
                    response: Response<List<DailyModel>>
                ) {
                    // Http 통신 결과 (200 코드대인 경우)
                    if (response.isSuccessful) {
                        // 서버 DB로부터 받은 DTO객체
                        val dailyModelList = response.body() as List<DailyModel>
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, response.code().toString())
                            Log.d(TAG, dailyModelList.toString())
                        }

                        cont.resumeWith(Result.success(dailyModelList)) // 코루틴 재게
                    }
                    // 서버로부터 에러 반환
                    else {
                        Log.e(TAG, response.code().toString())
                    }
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<List<DailyModel>>, cause: Throwable) {
                    Log.e(TAG, cause.message.toString())
                    cont.resumeWithException(cause)
                }
            })
        }


}