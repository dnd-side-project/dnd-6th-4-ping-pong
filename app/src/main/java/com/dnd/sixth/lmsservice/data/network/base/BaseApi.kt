package com.dnd.sixth.lmsservice.data.network.base

import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit

// HTTP통신을 할 Api 클래스들의 Root 클래스
abstract class BaseApi: KoinComponent {
    val retrofit: Retrofit by inject()

    // Refresh 토큰으로 Access 토큰 요청


}