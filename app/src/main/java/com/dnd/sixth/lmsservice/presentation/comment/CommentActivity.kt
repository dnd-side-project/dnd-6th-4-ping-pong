package com.dnd.sixth.lmsservice.presentation.comment

import android.graphics.Color
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCommentBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentActivity : BaseActivity<ActivityCommentBinding,CommentViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_comment

    override val viewModel: CommentViewModel by viewModel()

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

        initCheckBox()

    }

    //체크박스 초기화 메소드
    fun initCheckBox() {
        with(binding) {
            completeCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(completeCheckBox, completeText)


            }
            almostCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(almostCheckBox, almostText)

            }
            okayCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(okayCheckBox, okayText)

            }
            difficultCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(difficultCheckBox, difficultText)

            }
            hellCheckBox.setOnClickListener {
                checkUncheck()
                chekboxCheck(hellCheckBox, hellText)

            }
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

}