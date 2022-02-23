package com.dnd.sixth.lmsservice.presentation.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

import android.util.Log
import androidx.annotation.RequiresApi
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.databinding.ActivityLoginBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

import org.koin.androidx.viewmodel.ext.android.viewModel

import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()



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
                
                //로그인 성공 시 데이터 저장

            }


            //getAppKeyHash()



        }




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

