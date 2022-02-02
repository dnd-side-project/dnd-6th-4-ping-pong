package com.dnd.sixth.lmsservice.presentation.home.classes.manage

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassManageBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.making.MakeClassActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassManageFragment : BaseFragment<FragmentClassManageBinding, ClassManageViewModel>(), View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class_manage
    override val viewModel: ClassManageViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            classAddBtn.setOnClickListener(this@ClassManageFragment)
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.class_add_btn -> startActivity(Intent(requireContext(), MakeClassActivity::class.java))
        }
    }


}