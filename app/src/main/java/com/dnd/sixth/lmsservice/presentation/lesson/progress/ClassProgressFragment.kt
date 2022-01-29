package com.dnd.sixth.lmsservice.presentation.lesson.progress



import com.dnd.sixth.lmsservice.R

import com.dnd.sixth.lmsservice.databinding.FragmentClassProgressBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassProgressFragment : BaseFragment<FragmentClassProgressBinding, ClassProgressViewModel>() {
    override val layoutResId: Int
        get() = R.layout.fragment_class_progress

    override val viewModel: ClassProgressViewModel by viewModel()


    //액티비티 초기화 메서드
    override fun initActivity() {

        with(binding){
            viewModel = this@ClassProgressFragment.viewModel
        }
    }
}