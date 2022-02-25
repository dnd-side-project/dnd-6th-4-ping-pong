package com.dnd.sixth.lmsservice.domain.useCase.push.start

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class GetStartPushUseCase(private val pushRepository: PushRepository) {
    operator fun invoke(): Boolean =
        pushRepository.getStartPush()
}