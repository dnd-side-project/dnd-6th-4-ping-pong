package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.useCase.MakeSubjectUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.DayOfWeek
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.SalaryDay
import kotlinx.coroutines.launch

class CreateSubjectViewModel(
    private val makeSubjectUseCase: MakeSubjectUseCase
) : BaseViewModel() {

    var hour = 0 // 수업 시간 (Hour)
    var minute = 0 // 수업 시간 (Minute)

    // 수업 이름
    val className = MutableLiveData<String>()

    // 수업 요일 리스트
    private val _weekOfDayList = MutableLiveData(mutableListOf<DayOfWeek>())

    // 과외비 정산하는 시점 (ex. 4회차, 8회차, 12회차)
    private var salaryDay: SalaryDay = SalaryDay.FOUR

    // 완료 버튼을 누를 수 있는 여부
    private val _isDoneClickable = MutableLiveData<Boolean>(false)
    val isDoneClickable: LiveData<Boolean> = _isDoneClickable

    // 수업 생성 성공 여부
    private val _isMakeSuccess = MutableLiveData<Boolean?>()
    val isMakeSuccess: LiveData<Boolean> = _isMakeSuccess

    // 수업 요일 항목을 클릭
    fun onClickClassDow(dayOfWeek: DayOfWeek) {
        if (_weekOfDayList.value!!.contains(dayOfWeek)) {
            _weekOfDayList.value?.remove(dayOfWeek)
        } else {
            _weekOfDayList.value?.add(dayOfWeek)
        }

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
    fun setDoneClickable(): Boolean {
        val isNullClassName: Boolean = className.value.isNullOrBlank()
        val isEmptyList: Boolean = _weekOfDayList.value?.isEmpty() ?: true

        return !(isNullClassName || isEmptyList)
    }

    // 서버에 데이터를 전송하여 클래스 생성
    fun makeSubject(view: View) {
        viewModelScope.launch {
            val isSuccess = makeSubjectUseCase(SubjectEntity())
            _isMakeSuccess.postValue(isSuccess)
        }
    }
}