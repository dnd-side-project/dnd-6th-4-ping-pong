package com.dnd.sixth.lmsservice.data.repository.subject.datasource.base

import com.dnd.sixth.lmsservice.data.repository.emitter.ErrorType
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException

// HTTP통신을 할 Api 클래스들의 Root 클래스
interface BaseRemoteRepository {

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    // Refresh 토큰으로 Access 토큰 요청
    // (구현 예정)

    suspend fun <T> safeApiCall(
        emitter: RemoteErrorEmitter,
        callFunction: suspend () -> T
    ): T? {
        return try {
            val dispatcher = Dispatchers.IO
            val myObject = withContext(dispatcher) {
                callFunction.invoke()
            }
            myObject
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Timber.e(TAG, "Call error: ${e.localizedMessage}", e.cause)
                when (e) {
                    is HttpException -> {
                        if (e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
                        else {
                            val body = e.response()?.errorBody()
                            emitter.onError(getErrorMessage(body))
                        }
                    }
                    is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                    is IOException -> emitter.onError(ErrorType.NETWORK)
                    else -> emitter.onError(ErrorType.UNKNOWN)
                }
            }
            null
        }
    }


    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }

}