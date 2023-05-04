package com.dnd.sixth.lmsservice.domain.useCase.user.contact

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveLocalContactTimeUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(contactTime: String) {
        return userRepository.saveLocalContactTime(contactTime)
    }
}