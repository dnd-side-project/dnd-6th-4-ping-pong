package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.SignService
import com.dnd.sixth.lmsservice.data.network.service.SubjectService
import com.google.gson.annotations.SerializedName

class SubjectApi : BaseApi() {
    var api = retrofit.create(SubjectService::class.java)
}