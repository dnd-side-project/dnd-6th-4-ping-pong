package com.dnd.sixth.lmsservice.presentation.feedback

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.UpdateFeedBackUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class WriteFeedBackViewModel(
    var preferenceManager: PreferenceManager,
    var updateFeedBackUseCase: UpdateFeedBackUseCase
) : BaseViewModel() {

    var time = 0
    //과목 id
    var subjectID = 0
    //dailyClassID
    var dailyClassID = MutableLiveData<Int>()
    //오늘 수업은 어땠나요?
    var howClass = MutableLiveData<String>()
    //오늘의 한마디
    var feedbackContent = MutableLiveData<String>()

    fun assembleModel() : FeedBackModel{
        val modelFeedBack = FeedBackModel(
            dailyClassID.value!!,
            howClass.value.toString() + "#" + feedbackContent.value.toString(),
        )
        return modelFeedBack
    }

    fun writingComplete(feedback : FeedBackModel){
        viewModelScope.launch{
            updateFeedBackUseCase(feedback)
        }

    }


}