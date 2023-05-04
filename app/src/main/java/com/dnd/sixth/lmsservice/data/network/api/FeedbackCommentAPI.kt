package com.dnd.sixth.lmsservice.data.network.api

import com.dnd.sixth.lmsservice.data.network.base.BaseApi

import com.dnd.sixth.lmsservice.data.network.service.FeedBackService

class FeedbackCommentAPI : BaseApi() {
    var api = retrofit.create(FeedBackService::class.java)
}