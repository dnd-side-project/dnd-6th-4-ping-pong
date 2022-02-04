package com.dnd.sixth.lmsservice.presentation.inviteLink


import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityInviteLinkBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class InviteLinkActivity : BaseActivity<ActivityInviteLinkBinding,InviteLinkViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_invite_link

    override val viewModel: InviteLinkViewModel by viewModel()

    override fun initActivity() {

    }
}