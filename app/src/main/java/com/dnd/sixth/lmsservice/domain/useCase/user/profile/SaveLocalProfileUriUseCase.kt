package com.dnd.sixth.lmsservice.domain.usecase.user.profile

import android.net.Uri
import com.dnd.sixth.lmsservice.domain.repository.UserRepository

class SaveLocalProfileUriUseCase(private val userRepository: UserRepository) {
    operator fun invoke(profileUri: Uri) {
        userRepository.saveRemoteProfileUri(profileUri)
    }
}