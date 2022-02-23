package com.dnd.sixth.lmsservice.presentation.login

import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.response.UserResponse
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferenceManager: PreferenceManager,
) : BaseViewModel() {

    // 뷰모델 스코프 :  뷰모델이 onClear -> 코루틴도 모두 중단
    fun fetchData(): Job = viewModelScope.launch {

    }

    //자동 로그인 데이터 저장 & 로그인
    fun saveLoginInfo(userInfo : UserResponse){

        //if (autoSave){

        //SharedPreference에 id와 pw저장
        with(preferenceManager){

            setString(SAVED_ID_KEY,userInfo.email)
            setString(SAVED_PW_KEY,userInfo.password)
            setString(SAVED_NAME_KEY,userInfo.user_nm)
            setInt(SAVED_ROLE_KEY,userInfo.role)
            setString(SAVED_PHONE_NUMBER_KEY, userInfo.parent_phone_num)
            setInt(SAVED_UID_KEY,userInfo.id)
            //TODO 토큰 저장


        }


    }






}