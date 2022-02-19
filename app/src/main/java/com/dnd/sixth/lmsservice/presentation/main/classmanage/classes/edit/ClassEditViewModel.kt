package com.dnd.sixth.lmsservice.presentation.main.classmanage.classes.edit

import com.dnd.sixth.lmsservice.data.model.ClassModel
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class ClassEditViewModel : BaseViewModel() {
    // Intent에서 가져온 ClassItem
    var classModel: ClassModel? = null
}