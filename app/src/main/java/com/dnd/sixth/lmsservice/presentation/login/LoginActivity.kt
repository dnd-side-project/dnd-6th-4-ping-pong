package com.dnd.sixth.lmsservice.presentation.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.network.api.SignApi
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.response.UserResponse
import com.dnd.sixth.lmsservice.databinding.ActivityLoginBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpActivity
import com.dnd.sixth.lmsservice.presentation.main.MainActivity
import com.dnd.sixth.lmsservice.presentation.utility.AUTO_EMAIL_KEY
import com.dnd.sixth.lmsservice.presentation.utility.AUTO_PASSWORD_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ID_KEY
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

        autoLogin()

        viewModel.init()

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



            }




            //getAppKeyHash()



        }
        binding.loginAutoBtn.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                viewModel.saveDataCheck.value = isChecked
            }else {
                viewModel.saveDataCheck.value = isChecked
            }
        }

        //로그인 상태 유지 관찰자
        this.viewModel.saveDataCheck.observe(this@LoginActivity , androidx.lifecycle.Observer {
            binding.loginAutoBtn.isChecked = it
        })





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
                    Log.d("login","저장된 이메일은"+ userEmail)
                    viewModel.saveLoginInfo(userInfo)
                }
                Log.d("login", "SuccessGetUserInfo")
                //로그인 후 화면 전환
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)



            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                Toast.makeText(applicationContext,"이메일을 다시 한 번 확인해주세요",Toast.LENGTH_LONG).show()
            }

        })


    }

    fun autoLogin(){
        var email = viewModel.getStringFromViewModel(AUTO_EMAIL_KEY)
        var password = viewModel.getStringFromViewModel(AUTO_PASSWORD_KEY)

        Log.d("login","불러온 이메일은 "+ email!!)
        Log.d("login","사용한 키는 "+ AUTO_EMAIL_KEY)
        if(email ==""){
            return
        }
        LoginApi.api.getUserInfo(email!!).enqueue(object : Callback<UserResponse>{

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                var userInfo = response.body()
                if (userInfo != null) {
                    //로그인 정보 저장
                    viewModel.saveLoginInfo(userInfo)
                }
                Log.d("login", "SuccessAutoLogin")

                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("login", "FailAutoLogin")
            }

        })

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

