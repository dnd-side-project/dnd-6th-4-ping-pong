package com.dnd.sixth.lmsservice.domain.usecase.user.profile

import android.net.Uri
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveRemoteProfileUriUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: Number, profile: Uri): Uri? {
        return userRepository.saveRemoteProfileUri(uid, profile)
    }
}