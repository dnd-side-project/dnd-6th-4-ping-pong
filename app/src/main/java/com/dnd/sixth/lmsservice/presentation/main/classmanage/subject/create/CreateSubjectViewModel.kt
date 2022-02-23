package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.useCase.MakeSubjectUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.convertDowBit
import com.dnd.sixth.lmsservice.presentation.extensions.isAllFalse
import com.dnd.sixth.lmsservice.presentation.extensions.toggle
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.DayOfWeek
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.SalaryDay
import kotlinx.coroutines.launch

class CreateSubjectViewModel(
    private val makeSubjectUseCase: MakeSubjectUseCase,
    private val preferenceManager: PreferenceManager
) : BaseViewModel() {

    var hour = 0 // 수업 시간 (Hour)
    var minute = 0 // 수업 시간 (Minute)

    // 수업 이름
    val className = MutableLiveData<String>()

    // 수업 요일 리스트
    private val _weekOfDayList = MutableLiveData(
        mutableMapOf<DayOfWeek, Boolean>(
            DayOfWeek.MON to false,
            DayOfWeek.TUE to false,
            DayOfWeek.WED to false,
            DayOfWeek.THU to false,
            DayOfWeek.FRI to false,
            DayOfWeek.SAT to false,
            DayOfWeek.SUN to false,
        )
    )

    // 과외비 정산하는 시점 (ex. 4회차, 8회차, 12회차)
    private var salaryDay: SalaryDay = SalaryDay.FOUR

    // 완료 버튼을 누를 수 있는 여부
    private val _isDoneClickable = MutableLiveData<Boolean>(false)
    val isDoneClickable: LiveData<Boolean> = _isDoneClickable

    // 수업 생성 성공 여부
    private val _resultSubject = MutableLiveData<SubjectEntity?>()
    val resultSubject: LiveData<SubjectEntity?> = _resultSubject

    // 수업 요일 항목을 클릭
    fun onClickClassDow(dayOfWeek: DayOfWeek) {
        // 기존 값 Toggle
        _weekOfDayList.value!![dayOfWeek] =
            (_weekOfDayList.value?.get(dayOfWeek) as Boolean).toggle()

        // 수업 요일을 클릭할 때마다 '확인 클릭 여부' 설정
        setDoneClickable()
    }

    // 정산일을 선택했을 때 salaryDay 갱신
    fun onSelectSalaryDay(salaryDay: SalaryDay, isChecked: Boolean) {
        if (isChecked) {
            this.salaryDay = salaryDay
        }
    }

    /* 확인 버튼을 클릭이 가능한지 Boolean 반환
    *  @return true : 과외이름, 수업요일, 정산일이 정상적으로 선택이 된 경우
    *  @return false : else
    */
    fun setDoneClickable() {
        val isNullClassName: Boolean = className.value.isNullOrBlank()
        val isEmptyList: Boolean = _weekOfDayList.value?.isAllFalse() ?: true

        // 확인 클릭 가능 여부 갱신
        _isDoneClickable.value = (isNullClassName.not() && isEmptyList.not())
    }

    // 서버에 데이터를 전송하여 클래스 생성
    fun makeSubject(view: View) {

        viewModelScope.launch {
            val resultSubjectEntity = makeSubjectUseCase(
                SubjectEntity(
                    subjectName=className.value.toString(),
                    monthlyCnt=salaryDay.countInt,
                    classTime=App.instance.context.getString(R.string.hour_minute_format, hour, minute),
                    teacherId=preferenceManager.getInt("uid"),
                    color=DateColor.BLUE.ordinal,
                    classDowBit=_weekOfDayList.value!!.convertDowBit()
                )
            )

            _resultSubject.postValue(resultSubjectEntity)
        }
    }
}