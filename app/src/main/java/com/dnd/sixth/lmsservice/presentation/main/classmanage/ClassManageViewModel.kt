package com.dnd.sixth.lmsservice.presentation.main.classmanage

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.UserEntity
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetAllDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetAllSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.GetUserByUidUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.SubjectFragment
import com.dnd.sixth.lmsservice.presentation.utility.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClassManageViewModel(
    //private val getGeneralSubjectListUseCase: GetGeneralSubjectListUseCase,
    private val getAllSubjectListUseCase: GetAllSubjectListUseCase,
    private val getDailyClassUseCase: GetDailyClassUseCase,
    private val getAllDailyClassListUseCase: GetAllDailyClassListUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
    private val getUserByUidUseCase: GetUserByUidUseCase,
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
    private val _generalSubjectList = MutableLiveData(mutableListOf<GeneralSubjectEntity>())
    val generalSubjectDataList: LiveData<MutableList<GeneralSubjectEntity>> = _generalSubjectList

    private val _dailyClassList = MutableLiveData(mutableListOf<DailyEntity>())
    val dailyClassList: LiveData<MutableList<DailyEntity>> = _dailyClassList


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
            /*val generalSubjectList =
                getGeneralSubjectListUseCase(preferenceManager.getInt(SAVED_UID_KEY))*/
            /*
            *  일단 임시 방편으로 서버 DB에서 모든 정보를 불러와서 클라이언트에서 필터링하여 사용합니다.
            * */
            //val allUserList = getAllUserUseCase()
            val allSubjectList = getAllSubjectListUseCase()
            val allDailyList = getAllDailyClassListUseCase()
            val generalSubjectList = mutableListOf<GeneralSubjectEntity>()
            val myUid = getMyUid()

            val mySubjectIdList = mutableListOf<Int>() // 본인이 속한 수업 Id 리스트

            // 모든 수업 리스트를 필터링하며, 본인이 속한 수업 리스트를 생성합니다.
            val mySubjectList = allSubjectList.filter {
                if (isStudent()) { // 학생
                    myUid == it.studentId
                } else {
                    myUid == it.teacherId
                }
            }

            // 본인 수업 리스트를 순회하며, 수업 Id를 추가합니다.
            mySubjectList.forEach { subject ->
                subject.id?.toInt()?.let { mySubjectIdList.add(it) }
            }

            // 본인 수업 리스트를 순회하며,
            mySubjectList.forEach { subject ->
                val userEntity: UserEntity = if (isStudent()) { // 학생이면, 선생님 정보를 불러옵니다.
                    // Uid로 선생님의 정보를 불러옵니다.
                    getUserByUidUseCase(subject.teacherId!!.toInt())
                } else { // 선생님이면, 학생 정보를 불러옵니다.
                    getUserByUidUseCase(subject.studentId!!.toInt())
                }

                // Subject와 UserEntity로 GeneralSubject 객체를 만들어 리스트에 추가합니다.
                generalSubjectList.add(makeGeneralSubject(subject, userEntity))

            }

            /* // 본인이 속한 수업의 '일일 수업' 리스트
             val myDailyList = allDailyList?.filter {
                 mySubjectIdList.contains(it.subjectId)
             }*/

            // Dummy Data
            generalSubjectList.add(
                GeneralSubjectEntity(
                    "수학",
                    8,
                    "13 : 00 - 15 : 00",
                    3,
                    null,
                    DateColor.DARK_BLUE.ordinal,
                    "0000011",
                    "김철수",
                    1,
                    1,
                    Uri.parse("android.resource://"+ App.instance.getPackageName()+"/drawable/ic_temp_profile").toString(),
                    true
                )
            )


            _generalSubjectList.value = generalSubjectList

        }
    }

    // List에서 해당 position의 수업 데이터를 삭제합니다.
    fun removeSubject(position: Int) {
        _generalSubjectList.value = _generalSubjectList.value?.drop(position)!!.toMutableList()
    }

    // 수 클릭시 List에서 해당 position의 수업 삭제
    fun addSubject(subject: SubjectEntity) {
        _generalSubjectList.value = generalSubjectDataList.value?.apply{
            add(GeneralSubjectEntity(
                subject.subjectName,
                subject.monthlyCnt,
                subject.classTime,
                subject.teacherId,
                subject.studentId,
                subject.color,
                subject.classDayBit,
                "미연결 학생",
                0,
                0,
                null,
                false
            ))
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
            val value = generalSubjectEntity.otherName
            subjectIdToUserNameMap[key] = value
        }

        return subjectIdToUserNameMap
    }

    /* 
    *  Subject, User Entity로 GeneralSubject 객체를 생성합니다.
    *  */
    fun makeGeneralSubject(
        subject: SubjectEntity,
        userEntity: UserEntity
    ) = GeneralSubjectEntity(
        subject.subjectName,
        subject.monthlyCnt,
        subject.classTime,
        subject.teacherId,
        subject.studentId,
        subject.color,
        subject.classDayBit,
        userEntity.userName,
        subject.id!!,
        userEntity.id,
        userEntity.profileUrl,
    )


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


    private fun getRole(): Int {
        return preferenceManager.getInt(SAVED_ROLE_KEY)
    }

    fun getEmail(): String {
        return preferenceManager.getString(SAVED_ID_KEY) ?: ""
    }

    private fun isStudent(): Boolean {
        return getRole() == ROLE_STUDENT
    }

    private fun getMyUid(): Int = preferenceManager.getInt(SAVED_UID_KEY)

    fun getName(): String? = preferenceManager.getString(SAVED_NAME_KEY)

}