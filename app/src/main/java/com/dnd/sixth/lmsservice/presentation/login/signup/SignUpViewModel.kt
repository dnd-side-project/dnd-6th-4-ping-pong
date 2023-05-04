package com.dnd.sixth.lmsservice.presentation.login.signup


import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class SignUpViewModel : BaseViewModel() {


    //setValue가 제공되는 Mutable사용
    var pageNumber : MutableLiveData<Int> = MutableLiveData()
    var userInputInfo : MutableLiveData<Map<String, String>> = MutableLiveData()
    var passwordPass : MutableLiveData<Boolean> = MutableLiveData()
    var userRole : MutableLiveData<Int> = MutableLiveData()
//    //회원가입 요청 시 넘겨줄 정보
//    var email : String
//    var userNm : String
//    var password : String
//    var role = 2
//    var phoneNum : String = "0100101"
//    var parentPhoneNum : String? = null

    var count = 1
    var passOrNonPass = false
    var preRole = 0
    var mapUserInfo = mutableMapOf(
        "email" to "",
        "userNm" to "",
        "password" to "",
        "phoneNum" to "",
        "parentPhoneNum" to "")

    fun init() {
        pageNumber.value = count

        userInputInfo.value = mapUserInfo

        passwordPass.value = passOrNonPass
        userRole.value = preRole
    }




}