package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.generalclass.GeneralSubjectModel
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity


fun GeneralSubjectModel.toEntity(): GeneralSubjectEntity {
    return GeneralSubjectEntity(
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        this.teacherId,
        this.studentId,
        this.color,
        this.classDays,
        // 수업 듣는 요일을 Binary 형태로 표현한 String (ex. 1110111)
        // 8bit 데이터의 공백은 0으로 채웁니다.
        //String.format("%7s", Integer.toBinaryString(this.classDays.toInt())).replace("", "0"),
        this.studentName,
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
        this.teacherId,
        this.studentId,
        this.color,
        this.classDayBit,
        this.otherName,
        this.subjectId,
        this.userId,
        this.profileUri,
        this.isFeedbackChange
    )
}
