package com.dnd.sixth.lmsservice.domain.repository

import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity

interface FeedBackCommentRepository {
    /*
    *  Feedback 업데이트
    *
    * */
    suspend fun updateFeedBack(feedBackModel: FeedBackModel): Number?
}