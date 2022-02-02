package com.dnd.sixth.lmsservice.presentation.lesson.progress



import android.app.Activity
import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityClassProgressBinding

import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.TimeLineAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackActivity
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassProgressActivity : BaseActivity<ActivityClassProgressBinding, ClassProgressViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_class_progress

    //뷰모델 추가
    override val viewModel: ClassProgressViewModel by viewModel()

    val REQUEST_CODE = 100


    //액티비티 초기화 메서드
    override fun initActivity() {
        //
        with(binding){
            viewModel = this@ClassProgressActivity.viewModel

        }

        //어답터 추가
        with(binding){
            recyclerviewForTimeLine.adapter = TimeLineAdapter{ feedbackBtn ->
                val intent = Intent(this@ClassProgressActivity,WriteFeedBackActivity::class.java)
                startActivity(intent)
            }
            recyclerviewForTimeLine.layoutManager = LinearLayoutManager(this@ClassProgressActivity)

            progressStartFeedbackBtn.setOnClickListener {
                var intent = Intent(this@ClassProgressActivity,StartFeedBackActivity::class.java)
                startActivityForResult(intent,REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode != Activity.RESULT_OK){
                return
            }
            binding.recyclerviewForTimeLine.visibility = VISIBLE
            binding.progressStartFeedbackBtn.visibility = GONE
        }
    }







}