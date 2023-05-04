package com.dnd.sixth.lmsservice.data.network.interceptor

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.presentation.extensions.isTokenInvalid
import com.dnd.sixth.lmsservice.presentation.extensions.putToken
import okhttp3.Interceptor
import okhttp3.Response

// JWT 인증 토큰(Access Token)을 Header에 포함한 Interceptor
class AuthTokenInterceptor(private val prefManager: PreferenceManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // 세션 만료 여부를 위한 사용자 인증 Access Token
        val accessToken = prefManager.getAccessToken()

        // Header에 AccessToken 포함
        val request = chain.request().putToken(accessToken)

        // Access Token으로 얻은 응답 인스턴스
        val response = chain.proceed(request)

        // 만약 Access 토큰이 유효하지 않는다면
        if(response.isTokenInvalid()) {
            val refreshToken = prefManager.getRefreshToken() // Access 토큰을 갱신하기 위한 Refresh 토큰을 가져온다.
            val refreshRequest = chain.request().putToken(refreshToken) // Header에 Refresh Token을 담아 요청한다.

            // Refresh 토큰이 담긴 응답 반환
            return chain.proceed(refreshRequest)
        }

        // 토큰 만료 에러가 아니면 응답을 그대로 반환
        return response
    }
}