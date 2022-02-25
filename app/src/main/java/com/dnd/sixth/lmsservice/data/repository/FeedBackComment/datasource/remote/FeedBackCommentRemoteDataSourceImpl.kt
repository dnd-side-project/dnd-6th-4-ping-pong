package com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.network.api.DailyClassApi
import com.dnd.sixth.lmsservice.data.network.api.FeedbackCommentAPI
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.FeedBackEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resumeWithException

class FeedBackCommentRemoteDataSourceImpl(private val feedbackCommentAPI: FeedbackCommentAPI) :
    FeedBackCommentRemoteDataSource {
    override suspend fun updateFeedBack(
        remoteErrorEmitter: RemoteErrorEmitter,
        feedBackModel: FeedBackModel
    ): Number? = suspendCancellableCoroutine { cont ->

        val requestCall = feedbackCommentAPI.api.updateFeedBack(feedBackModel)

        val TAG = "FeedBackCommentRemoteDataSourceImpl"

        requestCall.enqueue(object : Callback<Number> {

            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<Number>,
                response: Response<Number>
            ) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    var isUpdated = response.body() as Number

                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, response.code().toString())

                    }

                    Log.d(TAG, "success Feedback update ${isUpdated}")
                    // 코루틴 재개
                    //성공 또는 실패한 결과를 마지막 일시 중단지점의 반환값으로 전달하는 코루틴의 실행을 다시 시작.
                    cont.resumeWith(Result.success(isUpdated))
                } else { // 서버로부터 에러 반환
                    Log.e(TAG, response.code().toString())
                }

            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<Number>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }

        })
    }


}