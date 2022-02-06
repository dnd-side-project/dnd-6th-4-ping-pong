package com.dnd.sixth.lmsservice.presentation.home.classes

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.ClassAdapter
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassManageViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.classes.config.ConfigActivity
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class ClassFragment : BaseFragment<FragmentClassBinding, ClassViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class
    override val viewModel: ClassViewModel by viewModel()

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

            setMarginTabItem() // 탭 간에 margin 설정
            setOnClickListener(binding)
        }
    }

    internal fun switchFragment() {
        val currentPage = binding.homeViewPager.currentItem
        binding.homeViewPager.currentItem = abs(currentPage - 1)
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun setOnClickListener(binding: FragmentClassBinding){
        with(binding) {
            configBtn.setOnClickListener(this@ClassFragment)
        }
    }

    private fun setMarginTabItem() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, UnitConverter.convertDPtoPX(requireContext(), 8), 0)
            tab.requestLayout()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.config_btn -> startActivity(Intent(requireContext(), ConfigActivity::class.java))
        }
    }

}