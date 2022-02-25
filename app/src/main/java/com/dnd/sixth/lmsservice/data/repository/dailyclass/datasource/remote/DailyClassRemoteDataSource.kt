package com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyModel
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity

interface DailyClassRemoteDataSource {
    suspend fun getDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyId: Int, subjectId: Int): DailyModel
    suspend fun createDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyModel: DailyModel): DailyModel?
    suspend fun getDailyClassList(remoteErrorEmitter: RemoteErrorEmitter, userId: Int): List<DailyModel>?
    suspend fun deleteDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyId: Int): DailyModel?
    suspend fun updateDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyModel: DailyModel): DailyModel?
    suspend fun getAllDailyClass(remoteErrorEmitter: RemoteErrorEmitter): List<DailyModel>
}