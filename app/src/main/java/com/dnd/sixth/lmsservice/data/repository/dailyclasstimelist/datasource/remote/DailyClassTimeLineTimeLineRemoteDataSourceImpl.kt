package com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dnd.sixth.lmsservice.BuildConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.network.api.DailyClassApi
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.FeedBackEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resumeWithException

class DailyClassTimeLineTimeLineRemoteDataSourceImpl(private val dailyClassApi: DailyClassApi) :
    DailyClassTimeLineRemoteDataSource {

    val TAG = "DailyClassRemoteDataSourceImpl"

    //임시
    override suspend fun createDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyClassEntity: DailyClassEntity
    ): DailyClassGetModel? {
        TODO("Not yet implemented")
    }

    //타임라인에 데일리클래스 불러오기
    override suspend fun getDailyClassList(
        remoteErrorEmitter: RemoteErrorEmitter,
        id: Int
    ): List<DailyClassGetModel> =


        //var result =

        suspendCancellableCoroutine{ cont ->
            // 서버에 전송할  Entity를 Model로 변환
            val requestCall = dailyClassApi.api.getAllDailyclass()

            //타임라인에 보여줄 데일리클래스 목록 리스트
            var timeLineList: MutableList<DailyClassGetModel> = mutableListOf()

            requestCall.enqueue(object : Callback<List<DailyClassGetModel>> {

                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<List<DailyClassGetModel>>,
                    response: Response<List<DailyClassGetModel>>
                ) {
                    // Http 통신 결과 (200 코드대인 경우)
                    if (response.isSuccessful) {
                        // 서버 DB로부터 받은 DTO객체
                        var dailyClassGetModel = response.body() as List<DailyClassGetModel>
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, response.code().toString())

                        }
                        Log.d("timeline", "찾는 과목 id :" +id.toString())
                        dailyClassGetModel.forEach {
                            //찾는 수업의 id와 같은 데일리클래스만 리스트에 추가
                            if (id.toString() == it.subjectId.toString() ) {
                                timeLineList.add(it)
                            }
                            Log.d("timeline", "foreach" +it.toString())


                        }

                        Log.d("timeline", "result " + timeLineList.toString())
                        // 코루틴 재개
                        //성공 또는 실패한 결과를 마지막 일시 중단지점의 반환값으로 전달하는 코루틴의 실행을 다시 시작.
                        cont.resumeWith(Result.success(timeLineList))
                    } else { // 서버로부터 에러 반환
                        Log.e(TAG, response.code().toString())
                    }

                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<List<DailyClassGetModel>>, cause: Throwable) {
                    Log.e(TAG, cause.message.toString())
                    cont.resumeWithException(cause)
                }

            })
        }



    override suspend fun deleteDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        Id: Int
    ): DailyClassGetModel? {
        TODO("Not yet implemented")
    }

    override suspend fun updateDailyClass(
        remoteErrorEmitter: RemoteErrorEmitter,
        dailyClassEntity: DailyClassEntity
    ): DailyClassGetModel? {
        TODO("Not yet implemented")
    }



    //Log.d("timeline", "result is" + result.toString())
        //return result




}