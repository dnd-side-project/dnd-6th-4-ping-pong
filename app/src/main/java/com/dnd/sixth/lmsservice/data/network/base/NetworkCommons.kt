package com.dnd.sixth.lmsservice.data.network.base

object NetworkCommons {

    const val BASE_URL = "http://54.157.75.57/" // Http 통신 Uri에 사용될 Base URL
    //const val BASE_URL = "http://localhost:3000/" // Http 통신 Uri에 사용될 Base URL
    const val CREDENTIAL_TYPE = "bearer" // Token Crendential 타입
    const val TIME_OUT = 5L // 타임아웃 시간 (단위 : Sec)
    const val AUTH_HEADER = "Authorization"
}