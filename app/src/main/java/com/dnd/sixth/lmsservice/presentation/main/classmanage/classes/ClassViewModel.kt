package com.dnd.sixth.lmsservice.presentation.main.classmanage.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.entity.ClassItem
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class ClassViewModel : BaseViewModel() {
    private val classList = arrayListOf<ClassItem>(
        ClassItem("1", "백지성", "1", "고3", "영어", listOf("월", "수"), true, "20:30", 0),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false, "10:30", 1),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false, "12:10", 2),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false, "20:30", 0),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false, "20:30", 0),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false, "13:00", 1),
    )

    /*
    private val classMutableLiveDataList = MutableLiveData<ArrayList<ClassItem>>(arrayListOf())
    val classLiveDataList: LiveData<ArrayList<ClassItem>> = classMutableLiveDataList
     */
    // 기본적으로는 emptyList() 를 담아둘 예정
    private val _classDataList = MutableLiveData<ArrayList<ClassItem>>(classList)
    val classDataList: LiveData<ArrayList<ClassItem>> = _classDataList

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = _classDataList.value?.size!! > 0
    fun getClassModel(position: Int): ClassItem {
        return _classDataList.value?.get(position) ?: throw Exception("Exist no item")
    }
}