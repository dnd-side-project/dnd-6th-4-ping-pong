package com.dnd.sixth.lmsservice.presentation.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityStartFeedBackBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

//없어진 피드백 디자인??
class StartFeedBackActivity : BaseActivity<ActivityStartFeedBackBinding,StartFeedBackViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_start_feed_back

    override val viewModel: StartFeedBackViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@StartFeedBackActivity.viewModel
            feedbackStartBtn.setOnClickListener {
                var intentFeedBackSet = Intent()
                setResult(RESULT_OK, intentFeedBackSet)
                finish()
            }

        }
    }
}