package com.dnd.sixth.lmsservice.presentation.communication.home

import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentCommunicationHomeBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunicationHomeFragment : BaseFragment<FragmentCommunicationHomeBinding, CommunicationHomeViewModel>() {

    override val layoutResId: Int
        get() = R.layout.fragment_communication_home

    override val viewModel: CommunicationHomeViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@CommunicationHomeFragment.viewModel
        }
    }


}