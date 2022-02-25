package com.dnd.sixth.lmsservice.presentation.comment

import android.graphics.Color
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.databinding.ActivityCommentBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentActivity : BaseActivity<ActivityCommentBinding,CommentViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_comment

    override val viewModel: CommentViewModel by viewModel()

    //인텐트로 넘겨받을 데일리클래스 ID
    val dailyClassId = 1

    //체크박스 컴포넌트 배열
    private val listOfCheckbox: MutableMap< CheckBox, TextView> by lazy {
        mutableMapOf(
            binding.completeCheckBox to binding.completeText,
            binding.almostCheckBox to binding.almostText,
            binding.okayCheckBox to binding.okayText,
            binding.difficultCheckBox to binding.difficultText,
            binding.hellCheckBox to binding.hellText
        )
    }


    override fun initActivity() {
        with(binding){
            viewModel = this@CommentActivity.viewModel
        }



        checkDailyID()

        initCheckBox()

        //뒤로가기
        binding.toolbarBtn.setOnClickListener {


            finish()
        }
        //완료
        binding.commentCompleteBtn.setOnClickListener {
            var commentModel = assembleCommentModel()
            viewModel.completeComment(commentModel)

            finish()
        }


    }

    //체크박스 초기화 메소드
    fun initCheckBox() {

            binding.completeCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(binding.completeCheckBox, binding.completeText)
                viewModel.imogeDifficulty?.value = 1
            }
        binding.almostCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(binding.almostCheckBox, binding.almostText)
                viewModel.imogeDifficulty.value = 2

            }
        binding.okayCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(binding.okayCheckBox, binding.okayText)
                viewModel.imogeDifficulty.value = 3

            }
        binding.difficultCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(binding.difficultCheckBox, binding.difficultText)
                viewModel.imogeDifficulty.value = 4

            }
        binding.hellCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(binding.hellCheckBox, binding.hellText)
                viewModel.imogeDifficulty.value = 5

            }

    }

    //다른 체크박스 클릭시 기존에 체크된 체크박스 순회하여 초기화하는 메소드
    private fun checkUncheck() {
        listOfCheckbox.forEach {
            it.key.isChecked = false
            it.value.setTextColor(Color.parseColor("#AAAAAA"))

        }
    }


    //체크박스 눌렀을 때 체크
    private fun chekboxCheck(checkbox: CheckBox, completeText: TextView) {
        checkbox.isChecked = true
        completeText.setTextColor(Color.parseColor("#FF000000"))
    }

    //해당 데일리클래스 id확인
    fun checkDailyID() {
        viewModel.dailyClassID.value = dailyClassId
    }

    fun assembleCommentModel() : CommentModel {
        //최종 피드백 내용 확인
        viewModel.dailyClassID.value = dailyClassId
        viewModel.comment.value = binding.commentQuestionEdittext.text.toString()

        //최종 피드백 내용을 가지고 요청 모델 조립 (api에 피드백 속성 하나만 요청할 수 있기에 한마디와 수업은 어땠나요 내용을 합침.)
        var model = viewModel.assembleModel()
        return model
    }

}