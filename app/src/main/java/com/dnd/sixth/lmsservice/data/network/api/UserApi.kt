package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.DailyService
import com.dnd.sixth.lmsservice.data.network.service.UserService

class UserApi: BaseApi() {
    var api: UserService = retrofit.create(UserService::class.java)
}