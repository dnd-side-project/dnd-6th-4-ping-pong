package com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote.FeedBackCommentRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository


class FeedBackCommentRepositoryImpl (
    private val feedbackCommentRemoteDataSource: FeedBackCommentRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
) : FeedBackCommentRepository {

    override suspend fun updateFeedBack(feedBackModel: FeedBackModel): Number? {
        return feedbackCommentRemoteDataSource.updateFeedBack(remoteErrorEmitter, feedBackModel)
    }

    override suspend fun getFeedBackFromDailyClass(subjectId: Number, dailyId: Number): DailyClassEntity? {
        return feedbackCommentRemoteDataSource.getFeedBackFromDailyClass(subjectId,dailyId)?.get(0)?.toEntity()
    }

    override suspend fun writeFeedBack(commentModel: CommentModel): Number? {
        return feedbackCommentRemoteDataSource.writeComment(remoteErrorEmitter, commentModel)
    }

    override suspend fun getCommentFromDailyClass(
        subjectId: Number,
        dailyId: Number
    ): DailyClassEntity? {
        return feedbackCommentRemoteDataSource.getCommentFromDailyClass(subjectId,dailyId)?.get(0)?.toEntity()
    }


}