package com.dnd.sixth.lmsservice.presentation.login

import android.content.Intent
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityLoginBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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

            buttonInit()





        }




    }

    // 로그인 타입 버튼 설정
    private fun buttonInit(){
        with(binding){
            loginStudentBtn.setOnClickListener {
                loginStudentBtn.setBackgroundColor(resources.getColor(R.color.signUpBtn))
                loginTeacherBtn.setBackgroundColor(resources.getColor(R.color.white))
            }
            loginTeacherBtn.setOnClickListener {
                loginStudentBtn.setBackgroundColor(resources.getColor(R.color.white))
                loginTeacherBtn.setBackgroundColor(resources.getColor(R.color.signUpBtn))

            }
        }

    }
}