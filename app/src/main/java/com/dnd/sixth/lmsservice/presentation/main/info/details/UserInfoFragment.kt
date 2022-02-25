package com.dnd.sixth.lmsservice.presentation.main.info.details

import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentUserInfoBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.main.info.InfoViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding, InfoViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_user_info
    override val viewModel: InfoViewModel by sharedViewModel()

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            // 역할에 따라 View의 시각화를 다르게 합니다.
            if (viewModel?.isStudent() == true) { // 학생인 경우
                studentContainer.visibility = View.VISIBLE
            } else { // 선생님인 경우
                teacherContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            // Fab 버튼 클릭시 수업 생성 액티비티로 이동

        }
    }

}