package com.dnd.sixth.lmsservice.data.model.feedbackComment

import com.google.gson.annotations.SerializedName

//서버에 요청할 Feedback Model
data class FeedBackModel (
    @SerializedName("id")
    val dailyClassId: Number,
    @SerializedName("daily_feedback")
    val dailyFeedBack: String
)

