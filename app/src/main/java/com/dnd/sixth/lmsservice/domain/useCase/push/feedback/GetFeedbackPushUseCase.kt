package com.dnd.sixth.lmsservice.domain.useCase.push.feedback

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class GetFeedbackPushUseCase(private val pushRepository: PushRepository) {
    operator fun invoke(): Boolean =
        pushRepository.getFeedbackPush()
}