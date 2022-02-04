package com.dnd.sixth.lmsservice.presentation.home.mypage

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentMyPageBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.mypage.profile.ProfileActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_my_page
    override val viewModel: MyPageViewModel by viewModel()


    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            optionProfileAccountBtn.setOnClickListener(this@MyPageFragment)
            optionNotificationBtn.setOnClickListener(this@MyPageFragment)
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_profile_account_btn -> {
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
            }
            R.id.option_notification_btn -> {

            }

        }
    }

}