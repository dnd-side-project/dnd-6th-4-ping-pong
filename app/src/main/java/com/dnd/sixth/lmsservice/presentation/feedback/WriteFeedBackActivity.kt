package com.dnd.sixth.lmsservice.presentation.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityWriteFeedBackBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WriteFeedBackActivity : BaseActivity<ActivityWriteFeedBackBinding,WriteFeedBackViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_write_feed_back

    override val viewModel: WriteFeedBackViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@WriteFeedBackActivity.viewModel
        }
    }
}