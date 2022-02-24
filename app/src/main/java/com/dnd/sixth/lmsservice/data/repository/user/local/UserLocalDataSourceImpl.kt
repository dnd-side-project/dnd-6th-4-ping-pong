package com.dnd.sixth.lmsservice.data.repository.user.local

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_CONTACT_TIME

class UserLocalDataSourceImpl(
    private val preferenceManager: PreferenceManager
): UserLocalDataSource {

    override fun saveContactTime(contactTime: String) {
        preferenceManager.setString(SAVED_CONTACT_TIME, contactTime)
    }

    override fun getContactTime(): String? = preferenceManager.getString(SAVED_CONTACT_TIME)

}