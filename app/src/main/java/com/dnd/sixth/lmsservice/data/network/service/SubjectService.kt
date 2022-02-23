package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SubjectService {
    @POST("subject")
    fun makeSubject(
        @Body subjectModel: SubjectModel
    ): Call<SubjectModel>

}