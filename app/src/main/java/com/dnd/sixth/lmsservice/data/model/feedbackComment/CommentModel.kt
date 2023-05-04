package com.dnd.sixth.lmsservice.data.model.feedbackComment

import com.google.gson.annotations.SerializedName

// 서버에 요청할 코멘트 모델
data class CommentModel(
    @SerializedName("id")
    val dailyClassId: Number,
    @SerializedName("daily_comment")
    val dailyComment: String,
    @SerializedName("difficulty")
    val difficulty: Number

)
