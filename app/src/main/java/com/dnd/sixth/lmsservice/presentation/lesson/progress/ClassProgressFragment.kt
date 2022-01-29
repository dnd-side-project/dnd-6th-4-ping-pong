package com.dnd.sixth.lmsservice.presentation.lesson.progress



import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.R

import com.dnd.sixth.lmsservice.databinding.FragmentClassProgressBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.TimeLineAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassProgressFragment : BaseFragment<FragmentClassProgressBinding, ClassProgressViewModel>() {
    override val layoutResId: Int
        get() = R.layout.fragment_class_progress

    //뷰모델 추가
    override val viewModel: ClassProgressViewModel by viewModel()


    //액티비티 초기화 메서드
    override fun initActivity() {
        //
        with(binding){
            viewModel = this@ClassProgressFragment.viewModel

        }

        //어답터 추가
        with(binding){
            recyclerviewForTimeLine.adapter = TimeLineAdapter()
            recyclerviewForTimeLine.layoutManager = LinearLayoutManager(requireContext())
        }
    }






}