package com.dnd.sixth.lmsservice.presentation.main.classmanage.config

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager

import com.dnd.sixth.lmsservice.domain.useCase.user.username.ChangeUserNameUseCase

import com.dnd.sixth.lmsservice.domain.usecase.user.profile.GetLocalProfileUriUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.profile.SaveLocalProfileUriUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.profile.SaveRemoteProfileUriUseCase


import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_NAME_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfigViewModel(
    private val changeUserNameUseCase: ChangeUserNameUseCase,
    private val saveRemoteProfileUriUseCase: SaveRemoteProfileUriUseCase,
    private val saveLocalProfileUriUseCase: SaveLocalProfileUriUseCase,
    private val getLocalProfileUriUseCase: GetLocalProfileUriUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {
    // EditText에 바인딩된 nickname
    val userName = MutableLiveData<String>(preferenceManager.getString(SAVED_NAME_KEY))
    val profileUri = MutableLiveData<Uri>(getLocalProfileUriUseCase())

    // 사용자의 닉네임을 변경합니다.
    suspend fun changeUserName(): Boolean {
        val currentName = preferenceManager.getString(SAVED_NAME_KEY) ?: "" // 현재 닉네임
        val uid = preferenceManager.getInt(SAVED_UID_KEY) // 유저의 Uid
        var isChanged = false // 닉네임이 변경되었는지에 대한 값을 가집니다.

        withContext(Dispatchers.IO) {
            if (userName.value != currentName) {
                val changedCount =
                    changeUserNameUseCase(uid, userName.value!!) // 닉네임 변경시, 데이터를 변경한 개수를 반환합니다.
                isChanged = changedCount > 0 // 닉네임이 변경되었는지 판단합니다.

                // 변경을 성공했다면, 로컬에 저장된 유저 이름도 변경합니다.
                if(isChanged) {
                    changeLocalUserName(userName.value!!)
                }
            }
        }

        return isChanged
    }

    // 로컬에 저장된 닉네임을 변경합니다.
    fun changeLocalUserName(newName: String) {
        preferenceManager.setString(SAVED_NAME_KEY, newName)
    }

    suspend fun updateProfileUri(newProfileUri: Uri?): Boolean = withContext(Dispatchers.IO) {
        var isUpdated = false
        val uid = preferenceManager.getInt(SAVED_UID_KEY)
        val resultUri = newProfileUri?.let { saveRemoteProfileUriUseCase(uid, it) }
        if (resultUri != null) {
            isUpdated = true
            saveLocalProfileUriUseCase(resultUri)
        }
        isUpdated
    }
}