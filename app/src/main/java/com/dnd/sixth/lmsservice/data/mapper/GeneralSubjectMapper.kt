package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.generalclass.GeneralSubjectModel
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity


fun GeneralSubjectModel.toEntity(): GeneralSubjectEntity {
    return GeneralSubjectEntity(
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        "2022-02-23",
        this.teacherId,
        this.color,
        this.teacherName,
        this.studentName,
        this.classDayBit,
        this.classId,
        this.userId,
        this.profileUri,
        this.isFeedbackChange
    )
}

fun GeneralSubjectEntity.toModel(): GeneralSubjectModel {
    return GeneralSubjectModel(
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        "2022-02-23",
        this.teacherId,
        this.color,
        this.teacherName,
        this.studentName,
        this.classDayBit,
        this.classId,
        this.userId,
        this.profileUri,
        this.isFeedbackChange
    )
}
