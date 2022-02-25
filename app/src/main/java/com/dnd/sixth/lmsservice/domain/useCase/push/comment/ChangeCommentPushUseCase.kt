package com.dnd.sixth.lmsservice.domain.useCase.push.comment

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class ChangeCommentPushUseCase(private val pushRepository: PushRepository) {
    suspend operator fun invoke(newStatus: Boolean) {
        pushRepository.changeCommentPush(newStatus)
    }
}