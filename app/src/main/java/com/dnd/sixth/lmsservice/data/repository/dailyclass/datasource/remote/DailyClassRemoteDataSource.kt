package com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.model.generalclass.GeneralSubjectModel
import com.dnd.sixth.lmsservice.data.model.subject.SubjectModel
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity

interface DailyClassRemoteDataSource {
    suspend fun createDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyClassEntity: DailyClassEntity): DailyClassGetModel?
    suspend fun getDailyClassList(remoteErrorEmitter: RemoteErrorEmitter , id : Int): List<DailyClassGetModel>?
    suspend fun deleteDailyClass(remoteErrorEmitter: RemoteErrorEmitter, Id: Int): DailyClassGetModel?
    suspend fun updateDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyClassEntity: DailyClassEntity): DailyClassGetModel?

}