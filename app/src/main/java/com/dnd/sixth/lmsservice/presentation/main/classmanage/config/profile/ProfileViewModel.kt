package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.usecase.user.GetLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.SaveLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.SaveRemoteContactTimeUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_UID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val preferenceManager: PreferenceManager,
    private val saveRemoteContactTimeUseCase: SaveRemoteContactTimeUseCase,
    private val saveLocalContactTimeUseCase: SaveLocalContactTimeUseCase,
    private val getLocalContactTimeUseCase: GetLocalContactTimeUseCase
): BaseViewModel() {

    private val _originContactTime = MutableLiveData<String?>(null)
    val originContactTime: LiveData<String?> = _originContactTime

    init {
        onIO {
            // Format : HH : mm ~ HH : mm
            val localContactTime = getLocalContactTimeUseCase()

            if(localContactTime.isNullOrBlank().not()) { // 기존에 이미 시간대가 설정되어 있는 경우엔 Null이 아닙니다.
                _originContactTime.postValue(localContactTime) // ViewModel 시간대 갱신

                val startTime = localContactTime!!.split("~")[0]
                val endTime = localContactTime.split("~")[1]

                startHour = startTime.split(":")[0].trim().toInt()
                startMin = startTime.split(":")[1].trim().toInt()

                endHour = endTime.split(":")[0].trim().toInt()
                endMin = endTime.split(":")[1].trim().toInt()
            }
        }
    }

    // 연락가능 시작 시간
    var startHour = 0
    var startMin = 0

    // 연락가능 끝나는 시간
    var endHour = 0
    var endMin = 0


    suspend fun saveRemoteContactTime(): Boolean =
        withContext(Dispatchers.IO) {
            val uid = preferenceManager.getInt(SAVED_UID_KEY) // 유저 UID
            val startContactTime = App.instance.getString(R.string.hour_minute_format, startHour, startMin) // Format = HH:mm
            val endContactTime = App.instance.getString(R.string.hour_minute_format, endHour, endMin) // Format = HH:mm
            val changedCount = saveRemoteContactTimeUseCase(uid, "$startContactTime:$endContactTime") // Format : HH:mm-HH:mm

            changedCount > 0
        }

    fun saveLocalContactTime() {
        onIO {
            // Format : HH : mm ~ HH : mm
            val contactTime = App.instance.getString(R.string.contact_time_format, startHour, startMin, endHour, endMin)
            saveLocalContactTimeUseCase(contactTime)
        }
    }

}