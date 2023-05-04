package com.dnd.sixth.lmsservice.data.network.base

import com.dnd.sixth.lmsservice.data.network.base.NetworkCommons.TIME_OUT
import com.dnd.sixth.lmsservice.data.network.interceptor.AuthTokenInterceptor
import com.dnd.sixth.lmsservice.data.network.interceptor.HttpLogInterceptorProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 레트로핏 빌더, 인스턴스 관련 함수들 모음 파일
 */

internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkCommons.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

internal fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

// Access Token 인터셉터를 포함한 OkHttpClient
internal fun buildOkHttpClientWithAccessToken(
    httpLogInterceptor: HttpLoggingInterceptor,
    //authTokenInterceptor: AuthTokenInterceptor
): OkHttpClient {

    val builder = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLogInterceptor) // Http 통신 Log를 출력하기 위한 Interceptor
     //   .addInterceptor(authTokenInterceptor) // Token 관련 Interceptor


    return builder.build()
}
