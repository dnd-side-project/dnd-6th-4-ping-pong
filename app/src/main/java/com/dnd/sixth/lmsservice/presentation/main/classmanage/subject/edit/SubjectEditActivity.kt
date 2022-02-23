package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.edit

import android.view.MenuItem
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityEditClassBinding
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubjectEditActivity : BaseActivity<ActivityEditClassBinding, SubjectEditViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_edit_class
    override val viewModel: SubjectEditViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        // 전달받은 ClassItem을 ViewModel에 초기화
        viewModel.generalSubjectEntity = (intent.getSerializableExtra("classModel") as GeneralSubjectEntity)

        with(binding) {
            viewModel = this@SubjectEditActivity.viewModel
            setSupportActionBar(toolbar) // 액션바 설정
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