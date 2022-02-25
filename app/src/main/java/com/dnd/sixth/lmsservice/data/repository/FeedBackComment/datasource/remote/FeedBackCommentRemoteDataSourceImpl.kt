package com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
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

class FeedBackCommentRemoteDataSourceImpl(
    private val feedbackCommentAPI: FeedbackCommentAPI,
    private val dailyClassApi: DailyClassApi
) :
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

    //피드백 받아올 때 데일리클래스를 불러옴
    override suspend fun getFeedBackFromDailyClass(subjectId: Number, dailyId: Number):
            List<DailyClassGetModel>? = suspendCancellableCoroutine { cont ->

        //현재 get DailyClass api 안 됨
        //하나만 받는 api 불가하여 전부 부르고 다시 추리기로함.
        val requestCall = dailyClassApi.api.getAllDailyclass()
        Log.d("timeline", "feedback get start" )

        val TAG = "FeedBackCommentRemoteDataSourceImpl"

        //결과로 반환될 데일리클래스 리스트
        var resultDailyClass: MutableList<DailyClassGetModel> = mutableListOf()

        requestCall.enqueue(object : Callback<List<DailyClassGetModel>> {

            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<List<DailyClassGetModel>>,
                response: Response<List<DailyClassGetModel>>
            ) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    var listOfDailyClass = response.body() as List<DailyClassGetModel>

                    listOfDailyClass.forEach {
                        //찾는 수업의 id와 같은 데일리클래스만 리스트에 추가
                        if (subjectId.toString() == it.subjectId.toString()
                            || dailyId.toString() == it.dailyClassId.toString()
                        ) {
                            resultDailyClass.add(it)
                            Log.d("timeline", "feedback class" + it.toString())
                        }

                    }
                    // 코루틴 재개
                    //성공 또는 실패한 결과를 마지막 일시 중단지점의 반환값으로 전달하는 코루틴의 실행을 다시 시작.
                    cont.resumeWith(Result.success(resultDailyClass))

                } else {
                    Log.d("timeline", "feedback get fail")

                }


            }


            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<List<DailyClassGetModel>>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }

        })


    }

    //코멘트 작성 메소드
    override suspend fun writeComment(
        remoteErrorEmitter: RemoteErrorEmitter,
        commentModel: CommentModel
    ): Number?  = suspendCancellableCoroutine { cont ->

        val requestCall = feedbackCommentAPI.api.updateComment(commentModel)

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



    //피드백 받아올 때 데일리클래스를 불러옴
    override suspend fun getCommentFromDailyClass(subjectId: Number, dailyId: Number):
            List<DailyClassGetModel>? = suspendCancellableCoroutine { cont ->

        //현재 get DailyClass api 안 됨
        //하나만 받는 api 불가하여 전부 부르고 다시 추리기로함.
        val requestCall = dailyClassApi.api.getAllDailyclass()
        Log.d("timeline", "feedback get start" )

        val TAG = "FeedBackCommentRemoteDataSourceImpl"

        //결과로 반환될 데일리클래스 리스트
        var resultDailyClass: MutableList<DailyClassGetModel> = mutableListOf()

        requestCall.enqueue(object : Callback<List<DailyClassGetModel>> {

            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<List<DailyClassGetModel>>,
                response: Response<List<DailyClassGetModel>>
            ) {
                // Http 통신 결과 (200 코드대인 경우)
                if (response.isSuccessful) {
                    // 서버 DB로부터 받은 DTO객체
                    var listOfDailyClass = response.body() as List<DailyClassGetModel>

                    listOfDailyClass.forEach {
                        //찾는 수업의 id와 같은 데일리클래스만 리스트에 추가
                        if (subjectId.toString() == it.subjectId.toString()
                            || dailyId.toString() == it.dailyClassId.toString()
                        ) {
                            resultDailyClass.add(it)
                            Log.d("timeline", "comment class" + it.toString())
                        }

                    }
                    // 코루틴 재개
                    //성공 또는 실패한 결과를 마지막 일시 중단지점의 반환값으로 전달하는 코루틴의 실행을 다시 시작.
                    cont.resumeWith(Result.success(resultDailyClass))

                } else {
                    Log.d("timeline", "feedback get fail")

                }


            }


            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<List<DailyClassGetModel>>, cause: Throwable) {
                Log.e(TAG, cause.message.toString())
                cont.resumeWithException(cause)
            }

        })


    }
}