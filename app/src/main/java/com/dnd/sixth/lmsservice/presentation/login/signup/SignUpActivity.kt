package com.dnd.sixth.lmsservice.presentation.login.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivitySignUpBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding,SignUpViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_sign_up

    override val viewModel: SignUpViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@SignUpActivity.viewModel
        }
    }


}