package com.dnd.sixth.lmsservice.presentation.home.classes

import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassManageBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassManageViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassManageFragment : BaseFragment<FragmentClassManageBinding, ClassManageViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class_manage
    override val viewModel: ClassManageViewModel by viewModel()

    var viewPagerFragmentAdapter: ClassManageViewPagerAdapter? = null

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            // FragmentStateAdapter 생성
            viewPagerFragmentAdapter = ClassManageViewPagerAdapter(requireActivity())

            // 생성한 adapter를 ViewPager와 연결
            homeViewPager.adapter = viewPagerFragmentAdapter
            homeViewPager.isUserInputEnabled = true // 뷰 페이저 슬라이드 허용
            TabLayoutMediator(tabLayout, homeViewPager) { tab, position ->
                tab.text = resources.getStringArray(R.array.home_tab_list)[position]
            }.attach() // 탭 클릭시 Fragment 전환
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            // Fab 버튼 클릭시 수업 생성 액티비티로 이동

        }
    }

}