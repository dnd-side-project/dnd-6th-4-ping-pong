package com.dnd.sixth.lmsservice.presentation.feedback.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCheckFeedBackBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckFeedBackActivity : BaseActivity<ActivityCheckFeedBackBinding,CheckFeedBackViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_check_feed_back

    override val viewModel: CheckFeedBackViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@CheckFeedBackActivity.viewModel
        }
    }


}