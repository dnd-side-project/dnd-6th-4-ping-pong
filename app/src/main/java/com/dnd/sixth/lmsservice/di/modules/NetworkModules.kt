package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.network.base.buildOkHttpClientWithAccessToken
import com.dnd.sixth.lmsservice.data.network.base.provideGsonConverterFactory
import com.dnd.sixth.lmsservice.data.network.base.provideRetrofit
import com.dnd.sixth.lmsservice.data.network.interceptor.AuthTokenInterceptor
import com.dnd.sixth.lmsservice.data.network.interceptor.HttpLogInterceptorProvider
import org.koin.dsl.module

val networkModules = module {
    single { provideGsonConverterFactory() }
    single {
        buildOkHttpClientWithAccessToken(
            get<HttpLogInterceptorProvider>().getInterceptor(),
            get()
        )
    }
    single { provideRetrofit(get(), get()) }
    single { HttpLogInterceptorProvider() }
    single { AuthTokenInterceptor(get()) }
}
