package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.SignService

class SignApi : BaseApi() {
    var api = retrofit.create(SignService::class.java)

}