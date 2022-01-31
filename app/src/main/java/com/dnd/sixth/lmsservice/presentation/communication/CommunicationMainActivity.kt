package com.dnd.sixth.lmsservice.presentation.communication


import com.dnd.sixth.lmsservice.R

import com.dnd.sixth.lmsservice.databinding.ActivityCommunicationMainBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.CommunicationPagerAdapter

import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

//  소통하기 파트의 화면의 뷰페이저를 위한 액티비티
class CommunicationMainActivity : BaseActivity<ActivityCommunicationMainBinding,CommunicationMainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_communication_main


    override val viewModel: CommunicationMainViewModel by viewModel()

    // 탭 레이아웃 아이콘 배열
    private val tabLayoutIconArray =
        arrayOf(R.drawable.img_temp, R.drawable.img_temp, R.drawable.img_temp)

    // ViewPager 어댑터
    lateinit var viewPagerFragmentAdapter: CommunicationPagerAdapter

    //액티비티 초기화 메서드
    override fun initActivity() {
        with(binding){
            viewModel = this@CommunicationMainActivity.viewModel

            communicationViewPager.adapter = viewPagerFragmentAdapter
            communicationViewPager.isUserInputEnabled = false // 뷰페이저 슬라이드 막기
            TabLayoutMediator(communicationTabLayout, communicationViewPager) { tab, position ->
                tab.setIcon(tabLayoutIconArray[position])
            }.attach() // 탭 클릭시 Fragment 전환
        }

        viewPagerFragmentAdapter = CommunicationPagerAdapter(this)
    }
}