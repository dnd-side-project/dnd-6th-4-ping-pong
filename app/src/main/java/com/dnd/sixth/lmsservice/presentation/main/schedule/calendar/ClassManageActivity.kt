package com.dnd.sixth.lmsservice.presentation.main.schedule.calendar

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCalendarBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassManageViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.schedule.add.ScheduleAddActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassManageActivity : BaseActivity<ActivityCalendarBinding, ClassManageViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_calendar
    override val viewModel: ClassManageViewModel by viewModel()
    private lateinit var viewPagerAdapter: ClassManageViewPagerAdapter

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
            viewPagerAdapter = ClassManageViewPagerAdapter(this@ClassManageActivity)

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

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.schedule_add_fab -> startActivity(Intent(this, ScheduleAddActivity::class.java))
        }
    }

}