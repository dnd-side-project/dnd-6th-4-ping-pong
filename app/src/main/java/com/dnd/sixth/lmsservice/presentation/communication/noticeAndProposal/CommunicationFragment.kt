package com.dnd.sixth.lmsservice.presentation.communication.noticeAndProposal

import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentCommunicationBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

//소통하기 홈화면의 CommunicationHomeActivity와 혼동 주의
class CommunicationFragment : BaseFragment<FragmentCommunicationBinding,CommunicationViewMoel> (){

    override val layoutResId: Int
        get() = R.layout.fragment_communication

    override val viewModel: CommunicationViewMoel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@CommunicationFragment.viewModel
        }
    }
}