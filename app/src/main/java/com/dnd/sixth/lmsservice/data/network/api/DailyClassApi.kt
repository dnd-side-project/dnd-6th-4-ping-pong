package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.DailyClassService
import com.dnd.sixth.lmsservice.data.network.service.SignService

class DailyClassApi : BaseApi() {
    var api = retrofit.create(DailyClassService::class.java)
}