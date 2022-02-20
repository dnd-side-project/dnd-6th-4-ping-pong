package com.dnd.sixth.lmsservice.presentation.extensions

import android.view.View
import com.dnd.sixth.lmsservice.data.network.base.NetworkCommons
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

fun List<String>.visibleIfContains(text: String, view: View) {
    if (this.contains(text)) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun List<String>.visibleViewListIfContain(textList: List<String>, view: List<View>) {

    for (i in 0..textList.size) {
        if (this.contains(textList[0])) {
            view[i].visibility = View.VISIBLE
        } else {
            view[i].visibility = View.GONE
        }
    }
}

// Request Header에 Access Token 또는 Refresh Token 추가
fun Request.putToken(token: String): Request {
    return this.newBuilder().addHeader(NetworkCommons.AUTH_HEADER, token).build()
}

fun Response.isTokenInvalid(): Boolean {
    val response = this // Access 토큰 유효 검사

    // 401 Error 반환시 (Access Token이 유효하지 않거나, 없는 경우)
    return response.code == HttpURLConnection.HTTP_UNAUTHORIZED
}