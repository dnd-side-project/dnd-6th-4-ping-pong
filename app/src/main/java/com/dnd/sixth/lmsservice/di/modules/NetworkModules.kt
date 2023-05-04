package com.dnd.sixth.lmsservice.di.modules


import com.dnd.sixth.lmsservice.data.network.api.DailyClassApi

import com.dnd.sixth.lmsservice.data.network.api.DailyApi
import com.dnd.sixth.lmsservice.data.network.api.FeedbackCommentAPI

import com.dnd.sixth.lmsservice.data.network.api.SubjectApi
import com.dnd.sixth.lmsservice.data.network.api.UserApi
import com.dnd.sixth.lmsservice.data.network.base.buildOkHttpClientWithAccessToken
import com.dnd.sixth.lmsservice.data.network.base.provideGsonConverterFactory
import com.dnd.sixth.lmsservice.data.network.base.provideRetrofit
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitterImpl
import com.dnd.sixth.lmsservice.data.network.interceptor.AuthTokenInterceptor
import com.dnd.sixth.lmsservice.data.network.interceptor.HttpLogInterceptorProvider
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import org.koin.dsl.module

val networkModules = module {
    single { provideGsonConverterFactory() }

    single {
        buildOkHttpClientWithAccessToken(
            get<HttpLogInterceptorProvider>().getInterceptor(),
            //get()
        )
    }

    single { provideRetrofit(get(), get()) }
    single { HttpLogInterceptorProvider() }
    single { AuthTokenInterceptor(get()) }

    single<RemoteErrorEmitter> {
        RemoteErrorEmitterImpl()
    }



    /* API Modules
    * */

    single {
        SubjectApi()

    }
    single {
        DailyClassApi()
    }
    single {
        DailyApi()
    }
    single {
        UserApi()
    }
    single {
        FeedbackCommentAPI()
    }

}
