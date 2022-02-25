package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.feedbackComment.CommentModel
import com.dnd.sixth.lmsservice.data.model.feedbackComment.FeedBackModel
import com.dnd.sixth.lmsservice.data.response.UserCreateDto
import com.dnd.sixth.lmsservice.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface FeedBackService {

    //코멘트 남기기
    @PUT("/dailyclass/review")
    fun updateComment(@Body myComment : CommentModel) : Call<Number>

    //피드백 남기기
    @PUT("/dailyclass/feedback")
    fun updateFeedBack(@Body myFeedback : FeedBackModel) : Call<Number>

}