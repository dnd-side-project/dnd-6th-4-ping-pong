package com.dnd.sixth.lmsservice.presentation.home.main.classes

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.entity.ClassItem
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class ClassViewModel : BaseViewModel() {
    private val classList = arrayListOf<ClassItem>(
        ClassItem("1", "백지성", "1", "고3", "영어", listOf("월", "수"), true),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
    )

    // 기본적으로는 emptyList() 를 담아둘 예정
    val classLiveDataList = MutableLiveData<ArrayList<ClassItem>>(classList)

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = classLiveDataList.value?.size!! > 0
}