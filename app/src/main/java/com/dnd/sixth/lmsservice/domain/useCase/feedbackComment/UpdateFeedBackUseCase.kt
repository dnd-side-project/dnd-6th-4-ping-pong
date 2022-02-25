package com.dnd.sixth.lmsservice.domain.useCase.feedbackComment

import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository

class UpdateFeedBackUseCase (private val feedBackCommentRepository: FeedBackCommentRepository) {
    suspend operator fun invoke(feedBackModel: FeedBackModel): Number? {
        return feedBackCommentRepository.updateFeedBack(feedBackModel)
    }
}