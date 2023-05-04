package com.dnd.sixth.lmsservice.data.repository.push.local

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.presentation.utility.PUSH_CLASS_START_KEY
import com.dnd.sixth.lmsservice.presentation.utility.PUSH_COMMENT_KEY
import com.dnd.sixth.lmsservice.presentation.utility.PUSH_FEEDBACK_KEY

class PushDataSourceImpl(private val preferenceManager: PreferenceManager): PushDataSource {
    override suspend fun changeStartPush(newStatus: Boolean) {
        preferenceManager.setBoolean(PUSH_CLASS_START_KEY, newStatus)
    }

    override suspend fun changeCommentPush(newStatus: Boolean) {
        preferenceManager.setBoolean(PUSH_COMMENT_KEY, newStatus)
    }

    override suspend fun changeFeedbackPush(newStatus: Boolean) {
        preferenceManager.setBoolean(PUSH_FEEDBACK_KEY, newStatus)
    }

    override fun getStartPush(): Boolean =
        preferenceManager.getBoolean(PUSH_CLASS_START_KEY)


    override fun getCommentPush(): Boolean =
        preferenceManager.getBoolean(PUSH_COMMENT_KEY)


    override fun getFeedbackPush(): Boolean =
        preferenceManager.getBoolean(PUSH_FEEDBACK_KEY)

}