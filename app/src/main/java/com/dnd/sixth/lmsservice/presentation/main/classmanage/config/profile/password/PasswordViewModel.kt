package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.useCase.user.password.ChangePasswordUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PasswordViewModel(
    val changePasswordUseCase: ChangePasswordUseCase,
    val preferenceManager: PreferenceManager
): BaseViewModel() {
    // 패스워드 show/hide Toggle 버튼이 체크되어 있는지 확인하기위한 변수
    var isCheckedEndIcon = true
    val newPassword = MutableLiveData<String>(null)

    // EndIcon Toggle
    fun toggleEndIcon() {
        isCheckedEndIcon = isCheckedEndIcon.not()
    }

    suspend fun changePassword(): Boolean {
        val uid = preferenceManager.getInt(SAVED_UID_KEY)
        var isChanged: Boolean
        withContext(Dispatchers.IO) {
            val changedCount = changePasswordUseCase(uid, newPassword.value!!)
            isChanged = changedCount > 0 // 비밀번호가 변경되었는지 판단합니다.
        }

        return isChanged
    }
}