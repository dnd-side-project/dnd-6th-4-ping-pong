package com.dnd.sixth.lmsservice.data.network.service

import com.dnd.sixth.lmsservice.data.model.generalclass.GeneralSubjectModel
import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import retrofit2.Call
import retrofit2.http.*

interface SubjectService {
    @POST("subject")
    fun createSubject(
        @Body subjectModel: SubjectModel
    ): Call<SubjectModel>

    @DELETE("subject")
    fun deleteSubject(
        @Query("id") subjectId: Int
    ): Call<SubjectModel>

    @GET("subject")
    fun getGeneralClassList(
        @Query("id") uid: Int
    ): Call<List<GeneralSubjectModel>>
}