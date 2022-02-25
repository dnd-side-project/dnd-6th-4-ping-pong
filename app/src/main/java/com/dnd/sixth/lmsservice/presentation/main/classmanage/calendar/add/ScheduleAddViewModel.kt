package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.CreateDailyClassUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAddViewModel(
    private val createDailyClassUseCase: CreateDailyClassUseCase
) : BaseViewModel() {

    // 완료 버튼을 누를 수 있는 여부
    private val _isDoneClickable = MutableLiveData<Boolean>(false)
    val isDoneClickable: LiveData<Boolean> = _isDoneClickable

    // 일정 : DateTimePicker로 선택한 날짜의 Calendar 데이터
    val pickedDate = MutableLiveData<Date>(null)

    lateinit var subjectIdUserNameMap: HashMap<Int, String>
    val subjectId = MutableLiveData<Int?>(null)
    val place = MutableLiveData<String?>(null) // 장소
    val chapter = MutableLiveData<String?>(null) // 챕터
    val memo = MutableLiveData<String?>(null) // 메모

    // 수업 회차 기본값
    private val defaultClassRound = 1

    // 수업 회차 LiveData
    var classRound = MutableLiveData<Int>(defaultClassRound)

    // 수업 생성 성공 여부
    private val _resultDaily = MutableLiveData<DailyEntity?>()
    val resultDaily: LiveData<DailyEntity?> = _resultDaily

    // 푸시 타임
    val _pushTime =
        MutableLiveData<PushTime>(PushTime.NONE) // 기본 값 null -> 나중에 null인 경우에는 없음으로 처리할 예정
    val pushTime: LiveData<PushTime> = _pushTime

    // 수업 회차 value + 1
    fun plusClassRound() {
        classRound.value = classRound.value?.plus(1)
    }

    // 수업 회차 value - 1
    fun minusClassRound() {
        classRound.value.let {
            // 수업 회차가 초기값(1)보다 클 때만 갱신한다
            if (it!! > defaultClassRound) {
                classRound.value = it.minus(1)
            }
        }
    }

    // 수업 회차를 특정 값으로 설정
    fun setClassRound(num: Int) {
        classRound.value = num
    }

    // 수업 회차를 기본 값으로 설정
    fun setDefaultClassRound() {
        classRound.value = defaultClassRound
    }

    // 새로운 푸시타임으로 변경
    fun changePushTime(newPushTime: PushTime?) {
        newPushTime?.let { _pushTime.value = it }
    }


    /* 완료 버튼을 클릭이 가능한지 Boolean 반환
    *  @return true : 수업시간, 장소, 챕터가 정상적으로 입력 된 경우
    *  @return false : else
    */
    fun setDoneClickable() {
        val isNullPlaceNull: Boolean = place.value?.isBlank() ?: true
        val isNullChapterBlank: Boolean = chapter.value?.isBlank() ?: true
        val isNullPickedDate: Boolean = pickedDate.value == null
        val isNullSubjectId: Boolean = (subjectId.value == null)

        // 확인 클릭 가능 여부 갱신
        _isDoneClickable.value = (isNullPlaceNull.not() && isNullChapterBlank.not() && isNullPickedDate.not() && isNullSubjectId.not())
    }

    // 수업 생성
    @SuppressLint("SimpleDateFormat")
    fun createDailyClass(view: View) {
        val dateFormat = SimpleDateFormat(DailyEntity.DATE_FORMAT)
        onIO {
            val resultDailyEntity = createDailyClassUseCase(
                DailyEntity(
                    subjectId = subjectId.value!!,
                    classOrder = classRound.value ?: 1,
                    startTime = dateFormat.format(pickedDate.value),
                    place = place.value!!,
                    chapter = chapter.value!!,
                    memo = memo.value ?: "",
                    noty = _pushTime.value?.timeText!!,
                )
            )

            _resultDaily.value = resultDailyEntity
        }
    }


}