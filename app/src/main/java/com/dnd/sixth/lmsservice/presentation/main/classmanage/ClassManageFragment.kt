package com.dnd.sixth.lmsservice.presentation.main.classmanage

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.databinding.FragmentClassManageBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassManageViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.ConfigActivity
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_NAME_KEY
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


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
            homeViewPager.isUserInputEnabled = false // 뷰 페이저 슬라이드 허용 안 함

            TabLayoutMediator(tabLayout, homeViewPager) { tab, position ->
                tab.text = resources.getStringArray(R.array.home_tab_list)[position]
            }.attach() // 탭 클릭시 Fragment 전환

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> classCountTextView.visibility = View.VISIBLE
                        1 -> classCountTextView.visibility = View.GONE
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            //setMarginTabItem() // 탭 간에 margin 설정
            setOnClickListener(binding)


            /* 클래스(수업) 및 캘린더 Fragment의 높이에 따라 ViewPager 스크롤을 위한 ScrollView의 높이를 업데이트해준다.  */
            ClassManageViewModel.screenHeight.observe(this@ClassManageFragment) { viewPagerHeight ->
                viewPagerScrollView.updateLayoutParams {
                    height = viewPagerHeight
                }
            }

            // SubjectFragment(ChildFragment)에서 수업 데이터를 가져오면 전체 수업 개수를 갱신한다.
            ClassManageViewModel.classCount.observe(this@ClassManageFragment) { classCount ->
                // 클래스 개수 텍스트 설정
                classCountTextView.text = getString(R.string.class_count_format, classCount)
            }


            // 화면에 Focus가 주어지면 이름 (새롭게) 설정
            binding.root.viewTreeObserver.addOnWindowFocusChangeListener { hasFocus ->
                if(hasFocus) {
                    // 인사말 텍스트뷰에 유저 이름 설정
                    helloNameTextView.text = getString(R.string.hello_format, viewModel?.getName())
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

    private fun setOnClickListener(binding: FragmentClassManageBinding) {
        with(binding) {
            configBtn.setOnClickListener(this@ClassManageFragment)
        }
    }

    // Tab 사이에 마진 설정
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
            // 설정 화면으로 이동
            R.id.config_btn -> startActivity(Intent(requireContext(), ConfigActivity::class.java))
        }
    }


}