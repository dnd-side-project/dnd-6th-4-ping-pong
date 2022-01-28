package com.dnd.sixth.lmsservice.presentation.main

import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityMainBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.MainViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    // 탭 레이아웃 아이콘 배열
    private val tabLayoutIconArray =
        arrayOf(R.drawable.img_temp, R.drawable.img_temp, R.drawable.img_temp)

    // ViewPager 어댑터
    lateinit var viewPagerFragmentAdapter: MainViewPagerAdapter


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
            // FragmentStateAdapter 생성
            viewPagerFragmentAdapter = MainViewPagerAdapter(this@MainActivity)

            // 생성한 adapter를 ViewPager와 연결
            mainViewPager.adapter = viewPagerFragmentAdapter
            mainViewPager.isUserInputEnabled = false // 뷰 페이저 슬라이드 막기
            TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
                tab.setIcon(tabLayoutIconArray[position])
            }.attach() // 탭 클릭시 Fragment 전환
        }
    }


}