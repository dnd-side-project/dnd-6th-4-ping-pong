package com.dnd.sixth.lmsservice.presentation.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

import android.util.Log
import androidx.annotation.RequiresApi
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.network.api.SignApi
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.response.UserResponse
import com.dnd.sixth.lmsservice.databinding.ActivityLoginBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpActivity
import com.dnd.sixth.lmsservice.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()

    //api
    var LoginApi : SignApi = SignApi()




    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@LoginActivity.viewModel

            //회원가입 버튼 클릭리스너
            loginSignupBtn.setOnClickListener {
                var intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }


            loginBtn.setOnClickListener{
                //login() 로그인 요청
                login()
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)


            }


            //getAppKeyHash()



        }





    }
    /*
        로그인
         */
    fun login(){
        //요청이 성공하였을 때



        var userEmail = binding.loginIdEdittext.text.toString()

        //로그인하면서 유저 정보 요청
        LoginApi.api.getUserInfo(userEmail).enqueue(object : Callback<UserResponse>{
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                var userInfo = response.body()
                if (userInfo != null) {
                    //로그인 정보 저장
                    viewModel.saveLoginInfo(userInfo)
                }
                Log.d("login", "SuccessGetUserInfo")



            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
    //자동 로그인
    fun autoLogin(){
        
    }



}

//    //sha-1 해시키 발급 함수
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getAppKeyHash() {
//            try {
//                val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//                for(i in info.signatures) {
//                    val md: MessageDigest = MessageDigest.getInstance("SHA")
//                    md.update(i.toByteArray())
//
//                    val something = String(Base64.getEncoder().encode(md.digest())!!)
//                    Log.e("Debug key", something)
//                }
//            } catch(e: Exception) {
//                Log.e("Not found", e.toString())
//            }
//        }

