package com.dnd.sixth.lmsservice.presentation.home

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityHomeBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.HomeViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.HomeCalendarFragment
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.HomeCalendarViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.add.ScheduleAddActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModel()
    private lateinit var viewPagerAdapter: HomeViewPagerAdapter

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData()
        initView()
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    private fun initView() {
        with(binding) {
            // FragmentStateAdapter 생성
            viewPagerAdapter = HomeViewPagerAdapter(this@HomeActivity)

            // 생성한 adapter를 ViewPager와 연결
            viewPager.adapter = viewPagerAdapter
            viewPager.isUserInputEnabled = false // 뷰 페이저 슬라이드 막기
            TabLayoutMediator(mainTabLayout, viewPager) { tab, position ->
                tab.text = resources.getStringArray(R.array.class_manage_tab_list)[position]
            }.attach() // 탭 클릭시 Fragment 전환
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onBackPressed() {
        if(supportFragmentManager.fragments.any { it is HomeCalendarFragment}) {
            // 뒤로가기 눌렀을 경우, 캘린더가 펼쳐져 있으면
            if(HomeCalendarViewModel.isExpanded.value == true) {
                finish() // 액티비티 종료
            } else { // 접혀져 있었다면 isExpanded 를 'True' 로 바꾸어 캘린더를 펼쳐준다
                HomeCalendarViewModel.isExpanded.value = true
            }
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.schedule_add_fab -> startActivity(Intent(this, ScheduleAddActivity::class.java))
        }
    }

}