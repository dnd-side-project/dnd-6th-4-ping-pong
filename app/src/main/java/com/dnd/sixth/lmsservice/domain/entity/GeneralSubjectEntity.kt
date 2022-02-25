package com.dnd.sixth.lmsservice.domain.entity

import java.io.Serializable

/*
*  Daily, Subject 데이터를 그룹핑한 Entity
* */
data class GeneralSubjectEntity(
    val subjectName: String,
    val monthlyCnt: Number,
    val classTime: String,
    val teacherId: Number?,
    val studentId: Number? = null,
    val color: Number,
    val classDayBit: String?, // 수업 요일 비트형태 'ex) 1000011'
    val otherName: String,
    val subjectId: Number, // 수업 Uid
    val userId: Number, // 학생 Uid
    val profileUri: String? = null, // 프로필 Uri
    val isFeedbackChange: Boolean = false // 피드백 업데이트 유무
) : Serializable