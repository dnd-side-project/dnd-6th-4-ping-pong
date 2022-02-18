package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.teacher.response

import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityEditResponseBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditResponseActivity :
    BaseActivity<ActivityEditResponseBinding, EditResponseViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_edit_response
    override val viewModel: EditResponseViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun initView() {
        with(binding) {
            setListener(this) // 리스너 설정
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    private fun setListener(binding: ActivityEditResponseBinding) {
        with(binding) {

        }
    }

    override fun onResume() {
        super.onResume()
    }



}