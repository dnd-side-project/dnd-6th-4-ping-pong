package com.dnd.sixth.lmsservice.data.repository.push.local

interface PushDataSource {
    suspend fun changeStartPush(newStatus: Boolean)
    suspend fun changeCommentPush(newStatus: Boolean)
    suspend fun changeFeedbackPush(newStatus: Boolean)

    fun getStartPush(): Boolean
    fun getCommentPush(): Boolean
    fun getFeedbackPush(): Boolean
}