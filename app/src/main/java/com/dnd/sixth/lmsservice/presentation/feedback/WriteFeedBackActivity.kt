package com.dnd.sixth.lmsservice.presentation.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.databinding.ActivityWriteFeedBackBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WriteFeedBackActivity : BaseActivity<ActivityWriteFeedBackBinding, WriteFeedBackViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_write_feed_back

    override val viewModel: WriteFeedBackViewModel by viewModel()

    //인텐트로 넘겨받을 데일리클래스 ID
    val dailyClassId = 1

    override fun initActivity() {

        checkDailyID()

        with(binding) {
            viewModel = this@WriteFeedBackActivity.viewModel
        }

        initView()
    }

    fun assembleFeedBackModel() : FeedBackModel {
        //최종 피드백 내용 확인
        viewModel.dailyClassID.value = dailyClassId
        viewModel.howClass.value = binding.todayHowClass.text.toString()
        viewModel.feedbackContent.value = binding.feedbackTodayCommentInput.text.toString()
        //최종 피드백 내용을 가지고 요청 모델 조립 (api에 피드백 속성 하나만 요청할 수 있기에 한마디와 수업은 어땠나요 내용을 합침.)
        var model = viewModel.assembleModel()
        return model
    }

    fun initView() {
        initButton()

    }

    fun initButton(){
        binding.feedbackCompleteBtn.setOnClickListener {
            //완료
            var model = assembleFeedBackModel()
            //요청
            viewModel.writingComplete(model)
            finish()
        }

        binding.toolbarBtn.setOnClickListener {
            finish()
        }

    }

    //해당 데일리클래스 id확인
    fun checkDailyID() {
        viewModel.dailyClassID.value = dailyClassId
    }

}