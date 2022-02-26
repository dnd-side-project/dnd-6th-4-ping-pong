package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity


//모델로 변환 (dto)
fun DailyClassEntity.toModel(): DailyClassGetModel {
    return DailyClassGetModel(
        dailyClassId = this.dailyClassId,
        subjectId = this.subjectId,
        classOrder = this.classOrder,
        startTime = this.startTime,
        place = this.place,
        chapter = this.chapter,
        memo = this.memo,
        dailyFeedback = this.dailyFeedback,
        dailyComment = this.dailyComment,
        homework = this.homework,
        isChangeFeedback = this.isChangeFeedback,
        difficulty = this.difficulty,
        noty = "what is noty"
    )

}

//엔티티로 변환
fun DailyClassGetModel.toEntity(): DailyClassEntity {
    return DailyClassEntity(

        subjectId = this.subjectId,
        classOrder = this.classOrder,
        startTime = this.startTime,
        place = this.place,
        chapter = this.chapter,
        memo = this.memo,
        dailyFeedback = this.dailyFeedback,
        dailyComment = this.dailyComment,
        homework = this.homework,
        isChangeFeedback = this.isChangeFeedback,
        noty = "what is noty",
        classDays = 2020-10-22,
        difficulty = 1,
        dailyClassId = this.dailyClassId,
    )
}



fun DailyModel.toEntity(): DailyEntity {
    return DailyEntity(
        this.id,
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
        this.id,
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

