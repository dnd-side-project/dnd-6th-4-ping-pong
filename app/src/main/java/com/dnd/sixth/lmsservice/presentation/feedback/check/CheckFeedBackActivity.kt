package com.dnd.sixth.lmsservice.presentation.feedback.check


import androidx.lifecycle.Observer
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCheckFeedBackBinding
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckFeedBackActivity : BaseActivity<ActivityCheckFeedBackBinding,CheckFeedBackViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_check_feed_back

    override val viewModel: CheckFeedBackViewModel by viewModel()

    //넘겨 받아야할 인텐트 값
    var subjectID = 1
    var dailyId = 1

    override fun initActivity() {


        with(binding){
            viewModel = this@CheckFeedBackActivity.viewModel
        }

        binding.toolbarBtn.setOnClickListener {
            finish()
        }






        viewModel.dailyClass.observe(this, Observer {
            binding.feedbackTodayCommentOutput.text = viewModel.dailyClass.value?.dailyFeedback?.split('#')?.get(0)
            //오늘의 한마디
            binding.outputFeedbackContent.text = viewModel.dailyClass.value?.dailyFeedback?.split('#')?.get(1)
            binding.feedbackClassTime.text = viewModel.dailyClass.value?.startTime + viewModel.dailyClass.value?.classDays
        })

        //해당과목 id로 조회
        viewModel.getInfoFeedBack(subjectID, dailyId)










    }








}