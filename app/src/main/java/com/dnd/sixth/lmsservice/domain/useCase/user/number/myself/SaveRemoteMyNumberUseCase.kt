package com.dnd.sixth.lmsservice.domain.usecase.user.number.myself

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveRemoteMyNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, myNumber: String): Int {
        return userRepository.saveRemoteMyNumber(uid, myNumber)
    }
}