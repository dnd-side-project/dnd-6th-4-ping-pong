package com.dnd.sixth.lmsservice.domain.useCase.user.number.parent

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetLocalParentNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): String? {
        return userRepository.getLocalParentNumber()
    }
}