package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.AuthService

class AuthApi: BaseApi() {
    val api = retrofit.create(AuthService::class.java)


}