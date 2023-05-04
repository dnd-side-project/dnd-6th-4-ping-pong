package com.dnd.sixth.lmsservice.presentation.lesson.progress

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.usecase.GetDailyClassTimeLineUseCase

import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ROLE_KEY
import kotlinx.coroutines.launch

class ClassProgressViewModel(
    var preferenceManager: PreferenceManager,
    var getDailyClassUseCase: GetDailyClassTimeLineUseCase
) : BaseViewModel() {

    //유저 타입 변수
    val role: MutableLiveData<Int> = MutableLiveData()

    //학생인경우 -> 숙제 관리 버튼 , 이미지(생각하는 여자)
    //선생님인 경우 -> 초대하기 버튼, 이미지( 생각하는 남자)

    fun checkRoleModel() {
        role.value = preferenceManager.getInt(SAVED_ROLE_KEY)

    }


    //타임라인에 띄울 수업 리스트
    var dailyClassList: MutableLiveData<List<DailyClassEntity>> = MutableLiveData()


    //타임라인 요청 메소드
    fun getTimeLineList(subjectId: Int) {
        viewModelScope.launch {
            //과목 id 추후 이전 액티비티에서 넘겨받을 필요 있음.
            var list = getDailyClassUseCase(subjectId)
            dailyClassList.value = list.orEmpty()
            dailyClassList.value = mutableListOf(
                DailyClassEntity(
                    1, 4, "10:30", "강남역 투썸플레이스", "수학",
                    "", "", "", "", "", false, 03.5, 0, 1
                ),
                DailyClassEntity(
                    1, 3, "10:30", "강남역 투썸플레이스", "수학",
                    "", "", "", "", "", false, 02.30, 0, 3
                ), DailyClassEntity(
                    1, 2, "10:30", "강남역 투썸플레이스", "수학",
                    "", "", "", "", "", false, 02.23, 0, 2
                ),
                DailyClassEntity(
                    1, 1, "10:30", "강남역 투썸플레이스", "수학",
                    "", "", "", "", "", false, 02.16, 0, 1
                )
            )
            Log.d("timeline", "getTimeLineList() " + list.toString())
        }

    }


}