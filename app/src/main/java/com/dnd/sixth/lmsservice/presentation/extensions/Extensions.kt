package com.dnd.sixth.lmsservice.presentation.extensions

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.network.base.NetworkCommons
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.DayOfWeek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

/*
* @param dowBinary : 0000001, 0110011 (요일을 비트화 해놓은 형태, 오른쪽부터 왼쪽으로 '월화수목금토일')
* */
fun visibleViewListIfContain(dayOfWeekBit: String, view: List<View>) {
    val containDate = '1'
    val notContainDate = '0'

    val dayOfWeekCharList = dayOfWeekBit.toCharArray() // 전달받은 요일(dayOfWeekBit)

    for (i in dayOfWeekCharList.size downTo 0) {
        if (dayOfWeekCharList[i] == containDate) {
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


// Boolean 값을 토글시켜 반환한다.
fun Boolean.toggle(): Boolean = this.not()


// DayOfWeek HashMap으로 부터 유저가 선택한 요일을 비트로 변환
fun Map<DayOfWeek, Boolean>.convertDowBit(): String {
    // 순서대로 (일토금목수화월)
    val dayOfWeekCharList = mutableListOf<Char>('0', '0', '0', '0', '0', '0', '0')

    var cursor = dayOfWeekCharList.size - 1 // 맨 마지막 인덱스부터 접근
    this.forEach { (_, isContains) ->
        if (isContains) { // 유저가 해당 날짜를 과외 요일에 포함했다면
            // 1로 갱신
            dayOfWeekCharList[cursor] = '1'
        }
        cursor -= 1
    }

    return dayOfWeekCharList.joinToString("")
}


// DayOfWeek HashMap으로 부터 유저가 선택한 요일을 비트로 변환
fun String.convertDowMap(): Map<DayOfWeek, Boolean> {
    val containDate = '1'
    // 순서대로 (일토금목수화월)
    val myDowBit = this.toCharArray() // ex. ('0', '0', '0', '1', '1', '1', '0')
    val dowMap = mutableMapOf<DayOfWeek, Boolean>(
        DayOfWeek.MON to false,
        DayOfWeek.TUE to false,
        DayOfWeek.WED to false,
        DayOfWeek.THU to false,
        DayOfWeek.FRI to false,
        DayOfWeek.SAT to false,
        DayOfWeek.SUN to false,
    )

    var idx = myDowBit.size - 1 // 맨 오른쪽이 값이 월요일이기 때문에 마지막 인덱스부터 접근
    dowMap.forEach { (key, _) ->
        // dowBit값과 containDate('1')이 같다면 수업이 있는 날입니다.
        dowMap[key] = (myDowBit[idx] == containDate)
        idx -= 1
    }

    return dowMap
}


// DayOfWeek HashMap의 Value가 모두 False인지 반환
fun Map<DayOfWeek, Boolean>.isAllFalse(): Boolean {

    // Values 중 하나라도 참이면 False반환
    this.values.forEach { isChecked ->
        if (isChecked) {
            return false
        }
    }
    return true
}


inline fun ViewModel.onMain(crossinline body: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch { body(this) }

inline fun ViewModel.onIO(crossinline body: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(Dispatchers.IO) { body(this) }

inline fun ViewModel.onDefault(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(Dispatchers.Default) { body(this) }
