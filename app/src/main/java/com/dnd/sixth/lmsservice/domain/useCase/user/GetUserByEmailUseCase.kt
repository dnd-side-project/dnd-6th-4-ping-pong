package com.dnd.sixth.lmsservice.domain.usecase.user

import com.dnd.sixth.lmsservice.domain.entity.UserEntity
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetUserByEmailUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String): UserEntity =
        userRepository.getUser(email)
}