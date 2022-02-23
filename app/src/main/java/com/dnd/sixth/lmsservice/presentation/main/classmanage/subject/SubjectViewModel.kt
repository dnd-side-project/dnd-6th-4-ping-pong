package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.domain.entity.DailyClass
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class SubjectViewModel : BaseViewModel() {
    private val classList = arrayListOf<SubjectEntity>()

    /*
    private val classMutableLiveDataList = MutableLiveData<ArrayList<ClassItem>>(arrayListOf())
    val classLiveDataList: LiveData<ArrayList<ClassItem>> = classMutableLiveDataList
     */
    // 기본적으로는 emptyList() 를 담아둘 예정
    private val _subjectList = MutableLiveData<ArrayList<SubjectEntity>>(classList)
    val dailyClassDataList: LiveData<ArrayList<SubjectEntity>> = _subjectList

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = _subjectList.value?.size!! > 0
    fun getClassModel(position: Int): SubjectEntity {
        return _subjectList.value?.get(position) ?: throw Exception("Exist no item")
    }
}