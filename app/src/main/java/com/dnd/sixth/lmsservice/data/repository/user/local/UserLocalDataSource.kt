package com.dnd.sixth.lmsservice.data.repository.user.local

interface UserLocalDataSource {
    fun saveContactTime(contactTime: String)
    fun getContactTime(): String?
}