package com.dnd.sixth.lmsservice.data.entity

import java.io.Serializable

data class ClassItem(
    val classId: String,
    val name: String,
    val profileUrl: String,
    val grade: String,
    val subject: String,
    val classDays: List<String>,
    val hasNotification: Boolean,
    val classTime: String,
    val salaryDateType: Int
): Serializable