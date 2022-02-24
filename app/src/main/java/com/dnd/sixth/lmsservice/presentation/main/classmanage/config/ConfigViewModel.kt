package com.dnd.sixth.lmsservice.presentation.main.classmanage.config

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.usecase.user.ChangeUserNameUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_NAME_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY

class ConfigViewModel(
    private val changeUserNameUseCase: ChangeUserNameUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {
    // EditText에 바인딩된 nickname
    val userName = MutableLiveData<String>(preferenceManager.getString(SAVED_NAME_KEY))

    // 사용자의 닉네임을 변경합니다.
    suspend fun changeUserName(): Boolean {
        val currentName = preferenceManager.getString(SAVED_NAME_KEY) ?: "" // 현재 닉네임
        val uid = preferenceManager.getInt(SAVED_UID_KEY) // 유저의 Uid
        var isChanged = false // 닉네임이 변경되었는지에 대한 값을 가집니다.

        onIO {
            if(userName.value != currentName) {
                val changedCount = changeUserNameUseCase(uid, currentName) // 닉네임 변경시, 데이터를 변경한 개수를 반환합니다.
                if(changedCount > 0) { // 닉네임이 변경되었다면
                    isChanged = true
                }
            }
        }

        return isChanged
    }
}