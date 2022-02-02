package com.dnd.sixth.lmsservice.presentation.login

import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityLoginBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@LoginActivity.viewModel


        }




    }

    private fun testShowFragment(){

    }
}