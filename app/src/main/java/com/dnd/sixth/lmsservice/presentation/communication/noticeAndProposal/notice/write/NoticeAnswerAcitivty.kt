package com.dnd.sixth.lmsservice.presentation.communication.noticeAndProposal.notice.write

import com.dnd.sixth.lmsservice.R

import com.dnd.sixth.lmsservice.databinding.ActivityNoticeAnswerBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeAnswerAcitivty : BaseActivity<ActivityNoticeAnswerBinding,NoticeAnswerViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_notice_answer

    override val viewModel: NoticeAnswerViewModel by viewModel()
    override fun initActivity() {
        with(binding){
            viewModel = this@NoticeAnswerAcitivty.viewModel
        }
    }
}