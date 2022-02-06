package com.dnd.sixth.lmsservice.presentation.home.classes.manage.edit

import android.view.MenuItem
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityEditClassBinding
import com.dnd.sixth.lmsservice.databinding.ActivityMakeClassBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditClassActivity : BaseActivity<ActivityEditClassBinding, EditClassViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_make_class
    override val viewModel: EditClassViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        with(binding) {
            viewModel = this@EditClassActivity.viewModel
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }
}