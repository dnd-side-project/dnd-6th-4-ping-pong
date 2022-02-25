package com.dnd.sixth.lmsservice.domain.usecase.user.number.parent

import android.util.Log
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class GetLocalParentNumberUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): String? {
        return userRepository.getLocalParentNumber()
    }
}