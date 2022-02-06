package com.dnd.sixth.lmsservice.presentation.home.classes.manage.making

import android.view.MenuItem
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityMakeClassBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakeClassActivity : BaseActivity<ActivityMakeClassBinding, MakeClassViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_make_class
    override val viewModel: MakeClassViewModel by viewModel()

    // 초대 코드 다이얼로그 TAG
    val TAG_INVITE_DIALOG_EVENT = "TAG_INVITE_DIALOG_EVENT"


    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@MakeClassActivity.viewModel
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_black_close_btn) // 뒤로가기 버튼 변경
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }
}