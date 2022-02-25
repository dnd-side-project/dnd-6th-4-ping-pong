package com.dnd.sixth.lmsservice.domain.entity

import com.google.gson.annotations.SerializedName

data class FeedBackEntity (
    val dailyClassId: Number,
    val dailyFeedBack: String
)