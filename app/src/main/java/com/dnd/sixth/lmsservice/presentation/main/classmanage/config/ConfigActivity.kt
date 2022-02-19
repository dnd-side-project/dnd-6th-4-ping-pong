package com.dnd.sixth.lmsservice.presentation.main.classmanage.config

import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityConfigBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.ProfileActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push.PushActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigActivity : BaseActivity<ActivityConfigBinding, ConfigViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_config
    override val viewModel: ConfigViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정

            optionAccountBtn.setOnClickListener(this@ConfigActivity)
            optionNotificationBtn.setOnClickListener(this@ConfigActivity)
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_account_btn -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.option_notification_btn -> {
                startActivity(Intent(this, PushActivity::class.java))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }


}