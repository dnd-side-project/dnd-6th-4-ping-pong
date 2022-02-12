package com.dnd.sixth.lmsservice.data.network

import androidx.databinding.ktx.BuildConfig
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val TIME_OUT = 5L // 타임아웃 시간 (단위 : Sec)

//레트로핏 빌더, 인스턴스 관련 함수들 모음 파일
internal fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

internal fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

internal fun buildOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {

    val builder = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)

    // 인터셉터 개수만큼 추가
    interceptors.forEach {
        builder.addInterceptor(it)
    }

    return builder.build()
}

// HTTP Log 출력을 위한 Interceptor
internal fun getHttpLoggingInterceptor() =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }


// JWT 인증 토큰을 Header 에 포함한 Interceptor
internal fun getJWTInterceptor(sharedPreferences: PreferenceManager) =
    object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val jwtToken = sharedPreferences.getIdToken()
            val builder = chain.request().newBuilder().header("Authorization", jwtToken!!)

            return chain.proceed(builder.build())
        }
    }

