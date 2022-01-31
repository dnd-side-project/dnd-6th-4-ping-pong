package com.dnd.sixth.lmsservice.presentation.communication.noticeAndProposal.notice.write

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R

import com.dnd.sixth.lmsservice.databinding.ActivityNoticeWriteBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeWriteActivity : BaseActivity<ActivityNoticeWriteBinding,NoticeWriteViewModel>(){
    override val layoutResId: Int
        get() = R.layout.activity_notice_write

    override val viewModel: NoticeWriteViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@NoticeWriteActivity.viewModel
        }
    }


}