package com.dnd.sixth.lmsservice.domain.useCase.user.contact

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetLocalContactTimeUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): String? {
        return userRepository.getLocalContactTime()
    }
}