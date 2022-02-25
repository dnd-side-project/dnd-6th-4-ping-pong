package com.dnd.sixth.lmsservice.domain.usecase.user

import com.dnd.sixth.lmsservice.domain.entity.UserEntity
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetUserByUidUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Int): UserEntity =
        userRepository.getUser(uid)
}