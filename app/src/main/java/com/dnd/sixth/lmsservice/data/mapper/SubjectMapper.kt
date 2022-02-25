package com.dnd.sixth.lmsservice.data.mapper

import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity


fun SubjectModel.toEntity(): SubjectEntity {
    return SubjectEntity(
        this.id,
        this.subjectName,
        this.monthlyCnt,
        this.classTime,
        "2022-02-23",
        this.teacherId,
        this.studentId,
        this.color,
        // 수업 듣는 요일을 Binary 형태로 표현한 String (ex. 1110111)
        // 8bit 데이터의 공백은 0으로 채웁니다.
        String.format("%7s", Integer.toBinaryString(this.classDayBit.toInt())).replace("", "0"),
    )
}

fun SubjectEntity.toModel(): SubjectModel {
    return SubjectModel(
        this.id,
        this.subjectName,
        0,
        this.monthlyCnt,
        this.classTime,
        this.classDate,
        this.teacherId,
        this.studentId,
        this.color,
        this.classDayBit.toInt()
    )
}
