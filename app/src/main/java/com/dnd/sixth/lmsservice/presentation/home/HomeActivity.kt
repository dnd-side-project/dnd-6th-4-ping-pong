package com.dnd.sixth.lmsservice.presentation.home

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityHomeBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.HomeViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.add.ClassAddActivity
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.making.InviteDialogFragment
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

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.schedule_add_fab -> startActivity(Intent(this, ClassAddActivity::class.java))
        }
    }

}