package com.dnd.sixth.lmsservice.presentation.login

import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ID_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_PW_KEY
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferenceManager: PreferenceManager,
) : BaseViewModel() {

    // 뷰모델 스코프 :  뷰모델이 onClear -> 코루틴도 모두 중단
    fun fetchData(): Job = viewModelScope.launch {

    }

    //자동 로그인 데이터 저장 & 로그인
    fun saveLoginInfo(userId : String, userPw : String){

        //if (autoSave){

        //SharedPreference에 id와 pw저장
        with(preferenceManager){

            setString(SAVED_ID_KEY,userId)
            setString(SAVED_PW_KEY,userPw)
            //setString(Saved_NAME_KEY,name)
            //setString(Saved_ROLE_KEY,role)
            //setString(Saved_PHONE_NUMBER_KEY, phone_number)
            
            //TODO 토큰 저장


        }

        //로그인
        login()
    }

    fun login(){
        //api명세서가 업데이트 되는대로 구현 예정
    }




}