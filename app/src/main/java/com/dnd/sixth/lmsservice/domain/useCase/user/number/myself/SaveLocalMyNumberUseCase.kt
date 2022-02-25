package com.dnd.sixth.lmsservice.domain.usecase.user.number.myself

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveLocalMyNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(myNumber: String) {
        return userRepository.saveLocalMyNumber(myNumber)
    }
}