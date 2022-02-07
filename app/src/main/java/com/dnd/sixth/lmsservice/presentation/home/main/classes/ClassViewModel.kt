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
    val classLiveDataList = MutableLiveData<ArrayList<ClassItem>>(classList)

}