package com.dnd.sixth.lmsservice.presentation.main.classmanage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.useCase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.SubjectFragment
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClassManageViewModel(
    private val getGeneralSubjectListUseCase: GetGeneralSubjectListUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {
    companion object {
        // ViewPager안에 있는 Fragment의 높이
        val screenHeight = MutableLiveData<Int>()

        // 선택된 Fragment (클래스, 캘린더) 의 이름
        val selectedFragmentName = MutableLiveData<String>(SubjectFragment::class.java.simpleName)
        val classCount = MutableLiveData<Int>(0)
    }


    // 기본적으로는 emptyList() 를 담아둔다.
    private val _generalSubjectList = MutableLiveData(listOf<GeneralSubjectEntity>())
    val generalSubjectDataList: LiveData<List<GeneralSubjectEntity>> = _generalSubjectList

    // 수업이 있는지 확인하기 위한 메서드
    fun hasClass() = _generalSubjectList.value?.size!! > 0
    fun getClassModel(position: Int): GeneralSubjectEntity {
        return _generalSubjectList.value?.get(position) ?: throw Exception("Exist no item")
    }

    init {
        // ViewModel 생성시 서버 DB에서 General Subject 리스트를 가져와 갱신합니다.
        updateGeneralSubjectList()
    }

    /* 서버 DB에서 General Subject 리스트를 가져옵니다.
    * */
    private fun updateGeneralSubjectList() {
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
            val deleteSubjectId = _generalSubjectList.value?.get(position)?.subjectId?.toInt()
            val deletedSubjectEntity = deleteSubjectId?.let { deleteSubjectUseCase(it) }
            (deletedSubjectEntity == null)
        }


    /* 수업(Subject) Id와 해당 수업의 User로 이루어진 Map 전달
   *  @K Key: SubjectId
   *  @V Value: UserName
   */
    fun getUserNameMap(): HashMap<Int, String> {
        val subjectIdToUserNameMap = HashMap<Int, String>()
        _generalSubjectList.value?.forEach { generalSubjectEntity ->
            val key = generalSubjectEntity.subjectId.toInt()
            val value = generalSubjectEntity.studentName
            subjectIdToUserNameMap[key] = value
        }

        return subjectIdToUserNameMap
    }

    /*
    * 수업Id와 색상 맵 반환
    * @Key : SubjectId
    * @Value : DateColor
    * */
    fun getDateColorMap(): Map<Int, DateColor> {
        val dateColorMap = HashMap<Int, DateColor>()
        _generalSubjectList.value?.forEach { generalSubjectEntity ->
            val key = generalSubjectEntity.subjectId.toInt()
            val value = when (generalSubjectEntity.color) {
                DateColor.RED.ordinal -> DateColor.RED
                DateColor.ORANGE.ordinal -> DateColor.ORANGE
                DateColor.YELLOW.ordinal -> DateColor.YELLOW
                DateColor.GREEN.ordinal -> DateColor.GREEN
                DateColor.BLUE.ordinal -> DateColor.BLUE
                DateColor.DARK_BLUE.ordinal -> DateColor.DARK_BLUE
                DateColor.PURPLE.ordinal -> DateColor.PURPLE
                else -> DateColor.RED
            }
            dateColorMap[key] = value
        }

        return dateColorMap
    }
}