package com.dnd.sixth.lmsservice.domain.usecase.user

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class ChangeUserNameUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, newName: String): Int {
        return userRepository.changeUserName(uid, newName)
    }
}