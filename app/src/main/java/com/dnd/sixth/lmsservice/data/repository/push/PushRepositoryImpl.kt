package com.dnd.sixth.lmsservice.data.repository.push

import com.dnd.sixth.lmsservice.data.repository.push.local.PushDataSource
import com.dnd.sixth.lmsservice.domain.repository.PushRepository

class PushRepositoryImpl(private val pushDataSource: PushDataSource): PushRepository {
    override suspend fun changeStartPush(newStatus: Boolean) {
        pushDataSource.changeStartPush(newStatus)
    }

    override suspend fun changeCommentPush(newStatus: Boolean) {
        pushDataSource.changeCommentPush(newStatus)
    }

    override suspend fun changeFeedbackPush(newStatus: Boolean) {
        pushDataSource.changeFeedbackPush(newStatus)
    }

    override fun getStartPush(): Boolean =
        pushDataSource.getStartPush()


    override fun getCommentPush(): Boolean =
        pushDataSource.getCommentPush()

    override fun getFeedbackPush(): Boolean =
        pushDataSource.getFeedbackPush()

}