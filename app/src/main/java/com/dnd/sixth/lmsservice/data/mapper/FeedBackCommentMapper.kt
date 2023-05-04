package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.entity.FeedBackEntity

fun FeedBackModel.toEntity(): FeedBackEntity {
    return FeedBackEntity(
        this.dailyClassId,
        this.dailyFeedBack
    )
}

fun FeedBackEntity.toModel(): FeedBackModel {
    return FeedBackModel(
        this.dailyClassId,
        this.dailyFeedBack

    )
}
