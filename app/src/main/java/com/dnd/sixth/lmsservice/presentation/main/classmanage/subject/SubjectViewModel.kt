package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.useCase.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubjectViewModel(
    private val getGeneralSubjectListUseCase: GetGeneralSubjectListUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {
    /*
    private val classMutableLiveDataList = MutableLiveData<ArrayList<ClassItem>>(arrayListOf())
    val classLiveDataList: LiveData<ArrayList<ClassItem>> = classMutableLiveDataList
     */
    // 기본적으로는 emptyList() 를 담아둘 예정
    private val _generalSubjectList = MutableLiveData(listOf<GeneralSubjectEntity>())
    val generalSubjectDataList: LiveData<List<GeneralSubjectEntity>> = _generalSubjectList

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = _generalSubjectList.value?.size!! > 0
    fun getClassModel(position: Int): GeneralSubjectEntity {
        return _generalSubjectList.value?.get(position) ?: throw Exception("Exist no item")
    }


    /*  General SubjectList를 서버 DB로부터 갱신
    * */
    fun updateGeneralSubjectList() {
        viewModelScope.launch {
            val generalSubjectList =
                getGeneralSubjectListUseCase(preferenceManager.getString(SAVED_UID_KEY)?.toInt()!!)
            _generalSubjectList.value = generalSubjectList

        }
    }

    /*  선택한 수업 삭제
    * */
    suspend fun deleteSubject(position: Int): Boolean =
        withContext(viewModelScope.coroutineContext) {
            val deleteSubjectId = _generalSubjectList.value?.get(position)?.classId?.toInt()
            val deletedSubjectEntity = deleteSubjectId?.let { deleteSubjectUseCase(it) }
            (deletedSubjectEntity == null)
        }


}