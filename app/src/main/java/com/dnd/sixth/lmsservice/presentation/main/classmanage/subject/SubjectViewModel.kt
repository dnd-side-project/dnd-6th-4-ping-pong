package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.domain.entity.DailyClass
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class SubjectViewModel : BaseViewModel() {
    private val classList = arrayListOf<DailyClass>()

    /*
    private val classMutableLiveDataList = MutableLiveData<ArrayList<ClassItem>>(arrayListOf())
    val classLiveDataList: LiveData<ArrayList<ClassItem>> = classMutableLiveDataList
     */
    // 기본적으로는 emptyList() 를 담아둘 예정
    private val _classDataList = MutableLiveData<ArrayList<DailyClass>>(classList)
    val dailyClassDataList: LiveData<ArrayList<DailyClass>> = _classDataList

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = _classDataList.value?.size!! > 0
    fun getClassModel(position: Int): DailyClass {
        return _classDataList.value?.get(position) ?: throw Exception("Exist no item")
    }
}