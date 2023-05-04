package com.dnd.sixth.lmsservice.domain.useCase.push.start

import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class ChangeStartPushUseCase(private val pushRepository: PushRepository) {
    suspend operator fun invoke(newStatus: Boolean) {
        pushRepository.changeStartPush(newStatus)
    }
}