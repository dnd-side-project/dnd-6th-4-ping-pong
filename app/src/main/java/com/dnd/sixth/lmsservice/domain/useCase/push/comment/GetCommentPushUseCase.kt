package com.dnd.sixth.lmsservice.domain.useCase.push.comment

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class GetCommentPushUseCase(private val pushRepository: PushRepository) {
    operator fun invoke(): Boolean =
        pushRepository.getCommentPush()
}