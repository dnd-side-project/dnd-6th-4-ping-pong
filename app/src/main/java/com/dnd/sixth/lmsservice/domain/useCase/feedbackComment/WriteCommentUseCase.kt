package com.dnd.sixth.lmsservice.domain.useCase.feedbackComment

import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository

class WriteCommentUseCase ( private val feedBackCommentRepository: FeedBackCommentRepository) {
    suspend operator fun invoke(commentModel : CommentModel): Number? {
        return feedBackCommentRepository.writeFeedBack(commentModel)
    }
}