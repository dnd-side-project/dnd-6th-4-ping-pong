package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push

import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.type.PushTime

class PushTimeViewModel: BaseViewModel() {
    // 선택한 푸시 타임
    var pushTime: PushTime? = null
    // 종료 플래그
    var finishFlag = MutableLiveData<Boolean>()

    fun setPushTime(view: View) {
        if(view is RadioButton) {
            when(view.id) {
                R.id.radio_all -> pushTime = PushTime.NONE
                R.id.radio_ten -> pushTime = PushTime.TEN
                R.id.radio_thirty -> pushTime = PushTime.THIRTY
                R.id.radio_one_hour -> pushTime = PushTime.ONE_HOUR
                R.id.radio_three_hour -> pushTime = PushTime.THREE_HOUR
            }
            // 액티비티 종료
            finishFlag.value = true
        }

    }
}