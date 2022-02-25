package com.dnd.sixth.lmsservice.domain.useCase.user.password

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class ChangePasswordUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, changeName: String): Int {
        return userRepository.changePassword(uid, changeName)
    }
}