package com.dnd.sixth.lmsservice.presentation.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityHomeworkManageBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.HomeworkManageAdapter
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.TimeLineAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackActivity
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackActivity
import kotlinx.android.synthetic.main.activity_class_progress.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeworkManageActivity : BaseActivity<ActivityHomeworkManageBinding, HomeworkManageViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_homework_manage


    override val viewModel: HomeworkManageViewModel by viewModel()


    override fun initActivity() {
        with(binding){
            viewModel = this@HomeworkManageActivity.viewModel
        }


        with(binding) {
            recyclerviewForHomework.layoutManager = LinearLayoutManager(this@HomeworkManageActivity)
            recyclerviewForHomework.adapter = HomeworkManageAdapter()

        }

    }
}