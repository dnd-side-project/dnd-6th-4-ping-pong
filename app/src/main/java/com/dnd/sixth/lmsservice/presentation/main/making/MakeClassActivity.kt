package com.dnd.sixth.lmsservice.presentation.main.making

import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityMakeClassBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakeClassActivity : BaseActivity<ActivityMakeClassBinding, MakeClassViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_make_class
    override val viewModel: MakeClassViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@MakeClassActivity.viewModel
        }
    }
}