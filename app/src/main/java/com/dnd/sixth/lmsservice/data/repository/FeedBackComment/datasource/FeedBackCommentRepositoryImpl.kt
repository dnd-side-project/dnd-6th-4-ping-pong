package com.dnd.sixth.lmsservice.data.repository.FeedBackComment

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote.FeedBackCommentRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassTimeLineRepository
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository


class FeedBackCommentRepositoryImpl (
    private val feedackCommentRemoteDataSource: FeedBackCommentRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
) : FeedBackCommentRepository {

    override suspend fun updateFeedBack(feedBackModel: FeedBackModel): Number? {
        return feedackCommentRemoteDataSource.updateFeedBack(remoteErrorEmitter, feedBackModel)
    }


}