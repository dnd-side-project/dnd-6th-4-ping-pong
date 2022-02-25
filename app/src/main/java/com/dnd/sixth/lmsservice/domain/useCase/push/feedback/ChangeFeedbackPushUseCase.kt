package com.dnd.sixth.lmsservice.domain.usecase.push.feedback

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class ChangeFeedbackPushUseCase(private val pushRepository: PushRepository) {
    suspend operator fun invoke(newStatus: Boolean) {
        pushRepository.changeFeedbackPush(newStatus)
    }
}