package com.dnd.sixth.lmsservice.presentation.main.info

import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityInfoBinding
import com.dnd.sixth.lmsservice.presentation.adapter.viewpager.ClassInfoViewPagerAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoActivity : BaseActivity<ActivityInfoBinding, InfoViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_info
    override val viewModel: InfoViewModel by viewModel()

    var viewPagerAdapter: ClassInfoViewPagerAdapter? = null

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
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false) // 뒤로가기 버튼 안보이게 설정
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정

            viewPagerAdapter = ClassInfoViewPagerAdapter(this@InfoActivity)
            viewPager.adapter = viewPagerAdapter
            viewPager.isUserInputEnabled = true
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                // 학생인 경우
                tab.text = resources.getStringArray(R.array.class_student_info_list)[position]

                // 선생님인 경우
                //tab.text = resources.getStringArray(R.array.class_teacher_info_list)[position]
            }.attach() // 탭 클릭시 Fragment 전환


        }
    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.close_btn -> finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }
}