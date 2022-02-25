package com.dnd.sixth.lmsservice.domain.useCase.user.number.parent

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveRemoteParentNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, parentNumber: String): Int {
        return userRepository.saveRemoteParentNumber(uid, parentNumber)
    }
}