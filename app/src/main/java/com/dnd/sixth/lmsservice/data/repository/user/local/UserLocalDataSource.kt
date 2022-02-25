package com.dnd.sixth.lmsservice.data.repository.user.local

import android.net.Uri

interface UserLocalDataSource {
    fun saveContactTime(contactTime: String)
    fun getContactTime(): String?

    fun saveMyNumber(myNumber: String)
    fun getMyNumber(): String?

    fun saveParentNumber(parentNumber: String)
    fun getParentNumber(): String?

    fun saveLocalProfileUri(profileUri: Uri)
    fun getLocalProfileUri(): Uri
}