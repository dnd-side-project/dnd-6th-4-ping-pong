package com.dnd.sixth.lmsservice.presentation.comment.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCheckCommentBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckCommentActivity : BaseActivity<ActivityCheckCommentBinding, CheckCommentViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_check_comment

    override val viewModel: CheckCommentViewModel by viewModel()

    override fun initActivity() {
        with(binding){
            viewModel = this@CheckCommentActivity.viewModel
        }
    }
}