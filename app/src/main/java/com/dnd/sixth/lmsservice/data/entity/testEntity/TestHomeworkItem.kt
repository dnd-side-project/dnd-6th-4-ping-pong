package com.dnd.sixth.lmsservice.data.entity.testEntity

data class TestHomeworkItem(
    val id: Long,
    val Date: String,
    val numbering: String,
    val homework: MutableMap<String, Boolean>?,
) {
    constructor() : this(0,"","", mutableMapOf("" to false))
}