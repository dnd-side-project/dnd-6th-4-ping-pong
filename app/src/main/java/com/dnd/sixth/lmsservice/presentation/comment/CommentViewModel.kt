package com.dnd.sixth.lmsservice.presentation.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.WriteCommentUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CommentViewModel(
    var preferenceManager: PreferenceManager,
    var writeCommentUseCase : WriteCommentUseCase
) : BaseViewModel() {


    var dailyClassID = MutableLiveData<Int>()
    //오늘 수업은 어땠나요?

    //오늘의 수업 난이도 12345 로 표현
    var imogeDifficulty = MutableLiveData<Int>()
    //메모를 작성해주세요.
    var comment = MutableLiveData<String>()


    fun completeComment(commentModel : CommentModel){
        viewModelScope.launch{
            writeCommentUseCase(commentModel)
        }
    }

    fun assembleModel() : CommentModel {
        val modelFeedBack = CommentModel(
            dailyClassID.value!!,
            comment.value.toString(),
            imogeDifficulty.value as Number
        )
        return modelFeedBack
    }



}