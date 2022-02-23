package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity


fun SubjectModel.toSubjectEntity(): SubjectEntity {
    return SubjectEntity(
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        "2022-02-23",
        this.teacherId,
        this.color,
        0b1000001
    )
}

fun SubjectEntity.toSubjectModel(): SubjectModel {
    return SubjectModel(
        this.subjectName,
        0,
        this.monthlyCnt,
        this.classTime,
        this.classDate,
        this.teacherId,
        this.color
    )
}
