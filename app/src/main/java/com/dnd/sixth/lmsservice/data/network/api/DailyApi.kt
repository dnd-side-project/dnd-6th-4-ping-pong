package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.DailyService

class DailyApi : BaseApi() {
    var api = retrofit.create(DailyService::class.java)
}