package com.dnd.sixth.lmsservice.presentation.comment.check

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCheckCommentBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckCommentActivity : BaseActivity<ActivityCheckCommentBinding, CheckCommentViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_check_comment

    override val viewModel: CheckCommentViewModel by viewModel()

    //넘겨 받아야할 인텐트 값
    var subjectID = 1
    var dailyId = 1

    override fun initActivity() {
        with(binding){
            viewModel = this@CheckCommentActivity.viewModel
        }

        binding.toolbarBtn.setOnClickListener {
            finish()
        }






        viewModel.dailyClass.observe(this, Observer {
            //메모를 작성해주세요
            binding.commentQuestionEdittext.text = viewModel.dailyClass.value?.dailyComment
            initImoge(viewModel.dailyClass.value?.difficulty!!)

        })

        //해당과목 id로 조회
        viewModel.getInfoComment(subjectID, dailyId)
    }

    fun initImoge(number : Int){
        when(number){
            0-> {
                return
            }
            1 ->{
                checkboxCheck(binding.completeCheckBox,binding.completeText)
            }
            2 ->{
                checkboxCheck(binding.almostCheckBox,binding.almostText)
            }
            3 ->{
                checkboxCheck(binding.okayCheckBox,binding.okayText)
            }
            4 ->{
                checkboxCheck(binding.difficultCheckBox,binding.difficultText)
            }
            5 ->{
                checkboxCheck(binding.hellCheckBox,binding.hellText)
            }
        }
    }

    //체크박스 눌렀을 때 체크
    fun checkboxCheck(checkbox: CheckBox, completeText: TextView) {
        checkbox.isChecked = true
        completeText.setTextColor(Color.parseColor("#FF000000"))
    }

}