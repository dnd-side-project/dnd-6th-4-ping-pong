package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push

import android.widget.CompoundButton
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.domain.useCase.push.comment.ChangeCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.comment.GetCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.feedback.ChangeFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.feedback.GetFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.start.ChangeStartPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.start.GetStartPushUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO

class PushViewModel(
    private val changeStartPushUseCase: ChangeStartPushUseCase,
    private val getStartPushUseCase: GetStartPushUseCase,
    private val changeCommentPushUseCase: ChangeCommentPushUseCase,
    private val getCommentPushUseCase: GetCommentPushUseCase,
    private val changeFeedbackPushUseCase: ChangeFeedbackPushUseCase,
    private val getFeedbackPushUseCase: GetFeedbackPushUseCase
) : BaseViewModel() {

    val startPushStatus = MutableLiveData<Boolean>(false)
    val commentPushStatus = MutableLiveData<Boolean>(false)
    val feedbackPushStatus = MutableLiveData<Boolean>(false)

    init {
        onIO {
            startPushStatus.postValue(getStartPushUseCase() ?: false)
            commentPushStatus.postValue(getCommentPushUseCase() ?: false)
            feedbackPushStatus.postValue(getFeedbackPushUseCase() ?: false)
        }

    }

    fun toggleStartPush(switch: CompoundButton, newStatus: Boolean) {
        onIO {
            changeStartPushUseCase(newStatus)
        }
    }

    fun toggleCommentPush(switch: CompoundButton, newStatus: Boolean) {
        onIO {
            changeCommentPushUseCase(newStatus)
        }
    }

    fun toggleFeedbackPush(switch: CompoundButton, newStatus: Boolean) {
        onIO {
            changeFeedbackPushUseCase(newStatus)
        }
    }

}