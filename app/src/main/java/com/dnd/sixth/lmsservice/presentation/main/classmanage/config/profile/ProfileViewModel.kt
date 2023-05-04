package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.GetLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.SaveLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.SaveRemoteContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.GetLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.SaveLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.SaveRemoteMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.GetLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.SaveLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.SaveRemoteParentNumberUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.utility.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    val preferenceManager: PreferenceManager,
    private val saveRemoteContactTimeUseCase: SaveRemoteContactTimeUseCase,
    private val saveLocalContactTimeUseCase: SaveLocalContactTimeUseCase,
    private val getLocalContactTimeUseCase: GetLocalContactTimeUseCase,
    private val getLocalMyNumberUseCase: GetLocalMyNumberUseCase,
    private val saveLocalMyNumberUseCase: SaveLocalMyNumberUseCase,
    private val saveRemoteMyNumberUseCase: SaveRemoteMyNumberUseCase,
    private val getLocalParentNumberUseCase: GetLocalParentNumberUseCase,
    private val saveLocalParentNumberUseCase: SaveLocalParentNumberUseCase,
    private val saveRemoteParentNumberUseCase: SaveRemoteParentNumberUseCase,
) : BaseViewModel() {

    // 유저 UID
    val uid = preferenceManager.getInt(SAVED_UID_KEY)

    // 로컬에 저장된 연락 가능 시간대
    var contactTime = MutableLiveData<String?>(null)

    // 로컬에 저장된 본인 휴대번호
    var myNumber = MutableLiveData<String?>(null)
    var originMyNumber: String? = null

    // 로컬에 저장된 학부모 휴대번호
    var parentNumber = MutableLiveData<String?>(null)
    var originParentNumber: String? = null

    init {

        /* Local에서 연락 가능 시간대를 가져옵니다. */
        if (preferenceManager.getInt(SAVED_ROLE_KEY) == ROLE_TEACHER) {
            onIO {
                // Format : HH : mm ~ HH : mm
                val localContactTime = getLocalContactTimeUseCase()

                if (localContactTime.isNullOrBlank().not()) { // 기존에 이미 시간대가 설정되어 있는 경우엔 Null이 아닙니다.
                    contactTime.postValue(localContactTime) // ViewModel 시간대 갱신

                    val startTime = localContactTime!!.split("~")[0]
                    val endTime = localContactTime.split("~")[1]

                    startHour = startTime.split(":")[0].trim().toInt()
                    startMin = startTime.split(":")[1].trim().toInt()

                    endHour = endTime.split(":")[0].trim().toInt()
                    endMin = endTime.split(":")[1].trim().toInt()
                }
            }
        }

        /* Local에서 저장된 사용자 휴대폰 번호를 가져옵니다. */
        onIO {
            val myPhoneNumber = getLocalMyNumberUseCase()
            if (myPhoneNumber.isNullOrBlank().not()) {
                originMyNumber = myPhoneNumber!!
                myNumber.postValue(myPhoneNumber!!)
            }
        }

        /* Local에서 저장된 학부모 휴대폰 번호를 가져옵니다. */
        if (preferenceManager.getInt(SAVED_ROLE_KEY) == ROLE_TEACHER) {
            onIO {
                val parentPhoneNumber = getLocalParentNumberUseCase()
                if (parentPhoneNumber.isNullOrBlank().not()) {
                    originParentNumber = parentPhoneNumber!!
                    parentNumber.postValue(parentPhoneNumber!!)
                }
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
            val startContactTime = App.instance.getString(
                R.string.hour_minute_format,
                startHour,
                startMin
            ) // Format = HH:mm
            val endContactTime = App.instance.getString(
                R.string.hour_minute_format,
                endHour,
                endMin
            ) // Format = HH:mm
            val changedCount = saveRemoteContactTimeUseCase(
                uid,
                "$startContactTime:$endContactTime"
            ) // Format : HH:mm-HH:mm

            changedCount > 0
        }

    fun saveLocalContactTime() {
        onIO {
            // Format : HH : mm ~ HH : mm
            val contactTime = App.instance.getString(
                R.string.contact_time_format,
                startHour,
                startMin,
                endHour,
                endMin
            )
            saveLocalContactTimeUseCase(contactTime)
        }
    }

    suspend fun saveRemoteMyNumber(): Boolean {
        return if (isNumberChanged()) {
            val changedCount: Int? = myNumber.value?.let { saveRemoteMyNumberUseCase(uid, it) }
            (changedCount ?: 0) > 0
        } else {
            false
        }
    }

    suspend fun saveLocalMyNumber() {
        if (isNumberChanged()) {
            myNumber.value?.let { saveLocalMyNumberUseCase(it) }
        }
    }

    suspend fun saveRemoteParentNumber(): Boolean {
        return if (isParentNumberChanged() && isStudent()) {
            val changedCount: Int? =
                parentNumber.value?.let { saveRemoteParentNumberUseCase(uid, it) }
            (changedCount ?: 0) > 0
        } else {
            false
        }
    }

    suspend fun saveLocalParentNumber() {
        if (isParentNumberChanged() && isStudent()) {
            parentNumber.value?.let { saveLocalParentNumberUseCase(it) }
        }
    }

    private fun getRole(): Int {
        return preferenceManager.getInt(SAVED_ROLE_KEY)
    }

    fun getEmail(): String {
        return preferenceManager.getString(SAVED_ID_KEY) ?: ""
    }

    fun isStudent(): Boolean {
        return getRole() == ROLE_STUDENT
    }

    // 본인 전화번호의 변경 유무를 반환합니다.
    private fun isNumberChanged(): Boolean {
        return ((myNumber.value != null) && originMyNumber != myNumber.value)
    }

    // 학부모 전화번호의 변경 유무를 반환합니다.
    private fun isParentNumberChanged(): Boolean {
        return ((parentNumber.value != null) && originParentNumber != parentNumber.value)
    }

}