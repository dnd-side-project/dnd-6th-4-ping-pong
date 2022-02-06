package com.dnd.sixth.lmsservice.presentation.home.main.config.profile

import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityProfileBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.home.main.config.profile.password.ChangePWActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_profile
    override val viewModel: ProfileViewModel by viewModel()

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

            setOnClickListener(this) // 클릭 리스너 설정
        }
    }
    
    private fun setOnClickListener(binding: ActivityProfileBinding) {
        with(binding) {
            passwordChangeBtn.setOnClickListener(this@ProfileActivity)
            passwordTitle.setOnClickListener(this@ProfileActivity)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.password_change_btn, R.id.password_title -> {
                startActivity(Intent(this, ChangePWActivity::class.java))
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