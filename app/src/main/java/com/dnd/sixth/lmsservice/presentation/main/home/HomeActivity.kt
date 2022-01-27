package com.dnd.sixth.lmsservice.presentation.main.home

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityHomeBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.making.MakeClassActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@HomeActivity.viewModel
            classAddFab.setOnClickListener(this@HomeActivity)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.class_add_fab -> startActivity(Intent(this, MakeClassActivity::class.java))
        }
    }

}