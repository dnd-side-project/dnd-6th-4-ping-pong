package com.dnd.sixth.lmsservice.presentation.main.studentmanage

import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentStudentManageBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StudentManageFragment : BaseFragment<FragmentStudentManageBinding, StudentManageViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_student_manage
    override val viewModel: StudentManageViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {

        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {

        }
    }

}