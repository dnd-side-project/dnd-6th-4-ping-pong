package com.dnd.sixth.lmsservice.domain.usecase.user.number.myself

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetLocalMyNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): String? {
        return userRepository.getLocalMyNumber()
    }
}