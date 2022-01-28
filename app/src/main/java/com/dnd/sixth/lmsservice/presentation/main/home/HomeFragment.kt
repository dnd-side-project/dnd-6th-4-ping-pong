package com.dnd.sixth.lmsservice.presentation.main.home

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityHomeBinding
import com.dnd.sixth.lmsservice.presentation.main.home.making.MakeClassActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<ActivityHomeBinding, HomeViewModel>(), View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            classAddFab.setOnClickListener(this@HomeFragment)
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            // Fab 버튼 클릭시 수업 생성 액티비티로 이동
            R.id.class_add_fab -> startActivity(
                Intent(
                    requireContext(),
                    MakeClassActivity::class.java
                )
            )
        }
    }

}