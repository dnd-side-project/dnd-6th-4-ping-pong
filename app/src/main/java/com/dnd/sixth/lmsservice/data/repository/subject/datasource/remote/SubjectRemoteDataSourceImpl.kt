package com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.data.mapper.toModel
import com.dnd.sixth.lmsservice.data.model.generalclass.GeneralSubjectModel
import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.data.network.api.SubjectApi
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resumeWithException

class SubjectRemoteDataSourceImpl(private val subjectApi: SubjectApi) : SubjectRemoteDataSource {

    val TAG = "SubjectRemoteDataSourceImpl"

    // 수업 생성
    override suspend fun createSubject(
        remoteErrorEmitter: RemoteErrorEmitter,
        subjectEntity: SubjectEntity
    ): SubjectModel? =
        suspendCancellableCoroutine { cont ->

            // 서버에 전송할 Subject Entity를 Model로 변환
            val requestCall = subjectApi.api.createSubject(subjectEntity.toModel())

            requestCall.enqueue(object : Callback<SubjectModel> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<SubjectModel>,
                    response: Response<SubjectModel>
                ) {

                    // Http 통신 결과 (200 코드대인 경우)
                    if (response.isSuccessful) {
                        // 서버 DB로부터 받은 DTO객체
                        val responseObject = response.body()
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, response.code().toString())
                            Log.d(TAG, responseObject.toString())
                        }

                        // 코루틴 재게
                        cont.resumeWith(Result.success(responseObject))
                    } else { // 서버로부터 에러 반환
                        Log.d(TAG, response.errorBody().toString())
                    }

                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<SubjectModel>, cause: Throwable) {
                    Log.e(TAG, cause.message.toString())
                    cont.resumeWithException(cause)
                }
            })

        }

    override suspend fun getGeneralClassList(
        remoteErrorEmitter: RemoteErrorEmitter,
        uid: Int
    ): List<GeneralSubjectModel>? =
        suspendCancellableCoroutine { cont ->
            val requestCall = subjectApi.api.getGeneralClassList(uid)

            requestCall.enqueue(object : Callback<List<GeneralSubjectModel>> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<List<GeneralSubjectModel>>,
                    response: Response<List<GeneralSubjectModel>>
                ) {
                    // Http 통신 결과 (200 코드대인 경우)
                    if (response.isSuccessful) {
                        // 서버 DB로부터 받은 DTO객체
                        val responseObject = response.body() as List<GeneralSubjectModel>

                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, response.code().toString())
                            Log.d(TAG, responseObject.toString())
                        }

                        // 코루틴 재게
                        cont.resumeWith(Result.success(responseObject))
                    } else { // 서버로부터 에러 반환
                        Timber.d(response.errorBody().toString())
                    }

                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<List<GeneralSubjectModel>>, cause: Throwable) {
                    Log.e(TAG, cause.message.toString())
                    cont.resumeWithException(cause)
                }
            })
        }
}
