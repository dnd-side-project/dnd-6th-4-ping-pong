package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi
import com.dnd.sixth.lmsservice.data.network.service.FeedbackService

class FeedbackApi : BaseApi() {
    val api = retrofit.create(FeedbackService::class.java)

}