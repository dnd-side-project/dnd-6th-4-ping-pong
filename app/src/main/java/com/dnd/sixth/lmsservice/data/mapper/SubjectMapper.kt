package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity


fun SubjectModel.toEntity(): SubjectEntity {
    return SubjectEntity(
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        "2022-02-23",
        this.teacherId,
        this.color,
        this.classDays
    )
}

fun SubjectEntity.toModel(): SubjectModel {
    return SubjectModel(
        this.subjectName,
        0,
        this.monthlyCnt,
        this.classTime,
        this.classDate,
        this.teacherId,
        this.color,
        this.classDays
    )
}
