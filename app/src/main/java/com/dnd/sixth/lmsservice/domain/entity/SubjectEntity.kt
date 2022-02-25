package com.dnd.sixth.lmsservice.domain.entity

import java.io.Serializable

data class SubjectEntity(
    val id: Number? = null,
    val subjectName: String,
    val monthlyCnt: Number,
    val classTime: String,
    val classDate: String = "2022-02-23",
    val teacherId: Number,
    val studentId: Number? = null,
    val color: Number,
    val classDayBit: String // 수업이 포함된 요일들 비트화
) : Serializable
