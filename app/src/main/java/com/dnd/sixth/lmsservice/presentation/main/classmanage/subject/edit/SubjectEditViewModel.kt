package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.edit

import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class SubjectEditViewModel : BaseViewModel() {
    // Intent에서 가져온 ClassItem
    var generalSubjectEntity: GeneralSubjectEntity? = null
}