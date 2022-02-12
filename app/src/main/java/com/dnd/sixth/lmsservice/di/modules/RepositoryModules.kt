package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.network.*
import com.dnd.sixth.lmsservice.data.network.buildOkHttpClient
import com.dnd.sixth.lmsservice.data.network.provideApiService
import com.dnd.sixth.lmsservice.data.network.provideGsonConverterFactory
import com.dnd.sixth.lmsservice.data.network.provideRetrofit

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModules = module{

    //SharedPreference
    single { PreferenceManager(androidContext()) }

    single{ provideGsonConverterFactory()}
    single{ buildOkHttpClient(get(), get())}
    single{ provideRetrofit(get(),get())}
    single { provideApiService(get()) }
    single { getHttpLoggingInterceptor() }
    single { getJWTInterceptor(get()) }

}