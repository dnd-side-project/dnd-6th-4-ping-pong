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
    val role : MutableLiveData<Int> = MutableLiveData()

    //학생인경우 -> 숙제 관리 버튼 , 이미지(생각하는 여자)
    //선생님인 경우 -> 초대하기 버튼, 이미지( 생각하는 남자)

    fun checkRoleModel(){
        role.value = preferenceManager.getInt(SAVED_ROLE_KEY)

    }



    //타임라인에 띄울 수업 리스트
  var dailyClassList : MutableLiveData<List<DailyClassEntity>> = MutableLiveData()




    //타임라인 요청 메소드
    fun getTimeLineList(subjectId : Int){
        viewModelScope.launch{
            //과목 id 추후 이전 액티비티에서 넘겨받을 필요 있음.
            var list = getDailyClassUseCase(subjectId)
            dailyClassList.value = list.orEmpty()
            Log.d("timeline","getTimeLineList() " + list.toString())
        }

    }



}