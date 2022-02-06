package com.dnd.sixth.lmsservice.presentation.home.classes.config.profile.password

import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class PasswordViewModel: BaseViewModel() {
    // 패스워드 show/hide Toggle 버튼이 체크되어 있는지 확인하기위한 변수
    var isCheckedEndIcon = true

    // EndIcon Toggle
    fun toggleEndIcon() {
        isCheckedEndIcon = isCheckedEndIcon.not()
    }
}