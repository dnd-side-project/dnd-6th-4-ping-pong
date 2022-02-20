package com.dnd.sixth.lmsservice.data.network.interceptor

import androidx.databinding.ktx.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

// HTTP Log 출력을 위한 Interceptor
class HttpLogInterceptorProvider {
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}