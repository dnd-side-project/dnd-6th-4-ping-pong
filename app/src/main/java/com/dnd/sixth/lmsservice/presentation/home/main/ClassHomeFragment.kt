package com.dnd.sixth.lmsservice.presentation.home.main

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.viewpager2.widget.ViewPager2
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassHomeBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassManageViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.HomeCalendarViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.classes.ClassFragment
import com.dnd.sixth.lmsservice.presentation.home.main.classes.ClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.config.ConfigActivity
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class ClassHomeFragment : BaseFragment<FragmentClassHomeBinding, ClassHomeViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class_home
    override val viewModel: ClassHomeViewModel by viewModel()

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
            homeViewPager.isUserInputEnabled = false // 뷰 페이저 슬라이드 허용 안 함
            TabLayoutMediator(tabLayout, homeViewPager) { tab, position ->
                tab.text = resources.getStringArray(R.array.home_tab_list)[position]
            }.attach() // 탭 클릭시 Fragment 전환

            setMarginTabItem() // 탭 간에 margin 설정
            setOnClickListener(binding)


            /* 클래스(수업) 및 캘린더 Fragment의 높이에 따라 ViewPager 스크롤을 위한 ScrollView의 높이를 업데이트해준다.  */
            ClassHomeViewModel.screenHeight.observe(this@ClassHomeFragment) { viewPagerHeight ->
                viewPagerScrollView.updateLayoutParams {
                    height = viewPagerHeight
                }
            }

        }
    }

    internal fun switchFragment() {
        val currentPage = binding.homeViewPager.currentItem
        binding.homeViewPager.currentItem = abs(currentPage - 1)
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun setOnClickListener(binding: FragmentClassHomeBinding) {
        with(binding) {
            configBtn.setOnClickListener(this@ClassHomeFragment)
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