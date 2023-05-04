package com.dnd.sixth.lmsservice.domain.useCase.user.contact

import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveRemoteContactTimeUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, contactTime: String): Int {
        return userRepository.saveRemoteContactTime(uid, contactTime)
    }
}