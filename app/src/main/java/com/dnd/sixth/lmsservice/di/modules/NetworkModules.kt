package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.network.base.*
import org.koin.dsl.module

val networkModules = module {
    single { provideGsonConverterFactory() }
    single {
        buildOkHttpClientWithAccessToken(
            getHttpLoggingInterceptor(),
            getAccessTokenInterceptor(get())
        )
    }
    single { provideRetrofit(get(), get()) }
    single { getHttpLoggingInterceptor() }
}
