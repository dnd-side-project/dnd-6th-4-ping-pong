package com.dnd.sixth.lmsservice.domain.useCase.feedbackComment

import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository

class GetCommentFromDailyUseCase (private val feedBackCommentRepository: FeedBackCommentRepository)  {
    suspend operator fun invoke(subjectId : Number, dailyId : Number): DailyClassEntity? {
        return feedBackCommentRepository.getCommentFromDailyClass(subjectId, dailyId)
    }
}