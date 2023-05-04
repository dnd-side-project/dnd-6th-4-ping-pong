package com.dnd.sixth.lmsservice.domain.useCase.user.number.parent

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveLocalParentNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(parentNumber: String) {
        return userRepository.saveLocalParentNumber(parentNumber)
    }
}