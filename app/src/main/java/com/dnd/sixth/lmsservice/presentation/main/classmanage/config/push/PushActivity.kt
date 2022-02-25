package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push

import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityPushBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_PARENT_PHONE_NUMBER_KEY
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class PushActivity : BaseActivity<ActivityPushBinding, PushViewModel>(){
    override val layoutResId: Int
        get() = R.layout.activity_push
    override val viewModel: PushViewModel by viewModel()

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData()
        initView()
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}