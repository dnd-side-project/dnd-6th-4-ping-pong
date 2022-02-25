package com.dnd.sixth.lmsservice.presentation.comment.check

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.usecase.GetDailyClassTimeLineUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CheckCommentViewModel(
    var preferecneManager: PreferenceManager,
    var getDailyClassTimeLineUseCase: GetDailyClassTimeLineUseCase
) : BaseViewModel() {

    //해당 Subject의수업 리스트
    var dailyClass: MutableLiveData<DailyClassEntity> = MutableLiveData()


    //타임라인 요청 메소드
    fun getInfoComment(subjectId: Int, dailyId: Int) {
        viewModelScope.launch {
            //과목 id 추후 이전 액티비티에서 넘겨받을 필요 있음.
            var list = getDailyClassTimeLineUseCase(subjectId)

            Log.d("comment", "list " + list.toString())
            Log.d("comment", "dailyId " + dailyId.toString())
            list?.forEach {
                if (it.dailyClassId.toString() == dailyId.toString()) {
                    //수업 리스트 중 해당 id를 가진 수업객체 -> 코멘트 참조
                    dailyClass.value = it
                } else {

                }


            }

            Log.d("feedback", "getInfoFeedBack() " + dailyClass.value.toString())
        }

    }


}