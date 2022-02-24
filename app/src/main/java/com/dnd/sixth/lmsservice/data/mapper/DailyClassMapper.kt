package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity


fun DailyModel.toEntity(): DailyEntity {
    return DailyEntity(
        this.subjectId,
        this.classOrder,
        this.startTime,
        this.place,
        this.chapter,
        this.memo,
        this.noty,
        this.dailyFeedback,
        this.dailyComment,
        this.homework,
        this.isChangeFeedback,
        this.changeStartTime,
        this.difficulty,
    )
}

fun DailyEntity.toModel(): DailyModel {
    return DailyModel(
        this.subjectId.toInt(),
        this.classOrder.toInt(),
        this.startTime,
        this.place,
        this.chapter,
        this.memo,
        this.noty,
        this.dailyFeedback,
        this.dailyComment,
        this.homework,
        this.isChangeFeedback,
        this.changeStartTime,
        this.difficulty,
    )
}