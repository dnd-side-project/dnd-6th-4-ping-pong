package com.dnd.sixth.lmsservice.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.network.api.SignApi
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.response.UserResponse
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {

    // 뷰모델 스코프 :  뷰모델이 onClear -> 코루틴도 모두 중단
    fun fetchData(): Job = viewModelScope.launch {

    }

    //api
    var LoginApi : SignApi = SignApi()

    //자동로그인 여부
    var saveDataCheck : MutableLiveData<Boolean> = MutableLiveData<Boolean>()



    fun init() {
        saveDataCheck.value = false

    }




    //자동 로그인 데이터 저장
    fun saveLoginInfo(userInfo : UserResponse){

        //로그인 상태 저장하시겠습니까
        if (saveDataCheck.value!!){
            preferenceManager.setString(AUTO_EMAIL_KEY,userInfo.email)
            preferenceManager.setString(AUTO_PASSWORD_KEY,userInfo.password)
        }

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

    fun getStringFromViewModel(key : String) : String?{
        return preferenceManager.getString(key)
    }

            //로그인 요청 api 업데이트 되었을 때 구현 예정
    fun autoLogin(){
        var email = preferenceManager.getString(AUTO_EMAIL_KEY).orEmpty()
        var password = preferenceManager.getString(AUTO_PASSWORD_KEY).orEmpty()
    }








}