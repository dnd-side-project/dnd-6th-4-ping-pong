package com.dnd.sixth.lmsservice.domain.entity

data class SubjectEntity(
    val subjectName: String,
    val monthlyCnt: Number,
    val classTime: String,
    val classDay: String,
    val teacherId: Number,
    val color: Number,
    val classDays: Number
)
