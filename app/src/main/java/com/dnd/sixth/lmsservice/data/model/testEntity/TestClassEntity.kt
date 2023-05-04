package com.dnd.sixth.lmsservice.data.model.testEntity

//테스트용 임시 수업 엔티티
data class TestClassEntity(
    val id : Long,
    val studentName : String,
    val className : String,
    val classDate : String,
    val classTime : String,
    val address : String,
    val number : Int
) {
    constructor() : this(0,"","","","","",0)
}