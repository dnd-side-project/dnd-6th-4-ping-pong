package com.dnd.sixth.lmsservice.data.repository.user.local

interface UserLocalDataSource {
    fun saveContactTime(contactTime: String)
    fun getContactTime(): String?

    fun saveMyNumber(myNumber: String)
    fun getMyNumber(): String?

    fun saveParentNumber(parentNumber: String)
    fun getParentNumber(): String?
}