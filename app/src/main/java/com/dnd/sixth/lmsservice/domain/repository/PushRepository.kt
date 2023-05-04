package com.dnd.sixth.lmsservice.domain.repository

interface PushRepository {
    suspend fun changeStartPush(newStatus: Boolean)
    suspend fun changeCommentPush(newStatus: Boolean)
    suspend fun changeFeedbackPush(newStatus: Boolean)

    fun getStartPush(): Boolean
    fun getCommentPush(): Boolean
    fun getFeedbackPush(): Boolean
}