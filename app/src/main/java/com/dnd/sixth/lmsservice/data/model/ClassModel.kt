package com.dnd.sixth.lmsservice.data.model

import java.io.Serializable

data class ClassModel(
    val classId: String,
    val name: String,
    val profileUrl: String,
    val grade: String,
    val subject: String,
    val classDays: List<String>,
    val hasNotification: Boolean,
    val classTime: String,
    val salaryDateType: Int,
): Serializable