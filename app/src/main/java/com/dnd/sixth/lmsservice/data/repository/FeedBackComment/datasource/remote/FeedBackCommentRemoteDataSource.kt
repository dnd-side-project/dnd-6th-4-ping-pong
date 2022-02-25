package com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote

import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.FeedBackEntity

interface FeedBackCommentRemoteDataSource {
    suspend fun updateFeedBack (remoteErrorEmitter: RemoteErrorEmitter, FeedBackModel: FeedBackModel): Number?
}