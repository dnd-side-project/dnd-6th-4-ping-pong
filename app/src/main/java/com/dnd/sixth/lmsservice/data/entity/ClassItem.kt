package com.dnd.sixth.lmsservice.data.entity

data class ClassItem(
    val classId: String,
    val name: String,
    val profileUrl: String,
    val grade: String,
    val subject: String,
    val classDays: List<String>,
    val hasNotification: Boolean
)