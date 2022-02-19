package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.ClassService

class ClassApi: BaseApi() {
    val api = retrofit.create(ClassService::class.java)

}