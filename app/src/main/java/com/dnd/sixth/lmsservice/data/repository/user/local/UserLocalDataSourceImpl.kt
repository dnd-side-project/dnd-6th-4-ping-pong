package com.dnd.sixth.lmsservice.data.repository.user.local

import android.net.Uri
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_CONTACT_TIME_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_PARENT_PHONE_NUMBER_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_PHONE_NUMBER_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_PROFILE_URI

class UserLocalDataSourceImpl(
    private val preferenceManager: PreferenceManager
) : UserLocalDataSource {

    override fun saveContactTime(contactTime: String) {
        preferenceManager.setString(SAVED_CONTACT_TIME_KEY, contactTime)
    }

    override fun getContactTime(): String? = preferenceManager.getString(SAVED_CONTACT_TIME_KEY)


    override fun saveMyNumber(myNumber: String) {
        preferenceManager.setString(SAVED_PHONE_NUMBER_KEY, myNumber)
    }

    override fun getMyNumber(): String? = preferenceManager.getString(SAVED_PHONE_NUMBER_KEY)


    override fun saveParentNumber(parentNumber: String) {
        preferenceManager.setString(SAVED_PARENT_PHONE_NUMBER_KEY, parentNumber)
    }

    override fun getParentNumber(): String? =
        preferenceManager.getString(SAVED_PARENT_PHONE_NUMBER_KEY)

    override fun saveLocalProfileUri(profileUri: Uri) {
        preferenceManager.setString(SAVED_PROFILE_URI, profileUri.toString())
    }

    override fun getLocalProfileUri(): Uri =
        Uri.parse(preferenceManager.getString(SAVED_PROFILE_URI))


}