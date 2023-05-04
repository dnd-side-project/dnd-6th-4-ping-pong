package com.dnd.sixth.lmsservice.presentation.main.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.UserEntity
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.GetUserByEmailUseCase
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.extensions.onIO
import com.dnd.sixth.lmsservice.presentation.utility.ROLE_STUDENT
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ROLE_KEY

class InfoViewModel(
    private val preferenceManager: PreferenceManager,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val getDailyClassUseCase: GetDailyClassUseCase,
) : BaseViewModel() {
    private val _subjectEntity = MutableLiveData<SubjectEntity>()
    private val _dailyEntity = MutableLiveData<DailyEntity>()
    private val _otherUserEntity = MutableLiveData<UserEntity>()

    val subjectEntity: LiveData<SubjectEntity> = _subjectEntity
    val dailyEntity: LiveData<DailyEntity> = _dailyEntity
    val otherUserEntity: LiveData<UserEntity> = _otherUserEntity


    private fun getRole(): Int {
        return preferenceManager.getInt(SAVED_ROLE_KEY)
    }

    fun isStudent(): Boolean {
        return getRole() == ROLE_STUDENT
    }

    fun getSubjectEntity(subjectId: Int) {
        onIO {
            _subjectEntity.postValue(getSubjectUseCase(subjectId)!!)
        }
    }

    fun getDailyClass(dailyId: Int, subjectId: Int) {
        onIO {
            getDailyClassUseCase(dailyId, subjectId)?.let { _dailyEntity.postValue(it) }
        }
    }

    fun getOtherUserEntity(otherEmail: String) {
        onIO {
            getUserByEmailUseCase(otherEmail)?.let { _otherUserEntity.postValue(it) }
        }
    }

}