package com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote

import com.dnd.sixth.lmsservice.data.model.dailyclass.DailyClassGetModel
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.FeedBackEntity

interface DailyClassTimeLineRemoteDataSource {
    suspend fun createDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyClassEntity: DailyClassEntity): DailyClassGetModel?
    suspend fun getDailyClassList(remoteErrorEmitter: RemoteErrorEmitter , id : Int): List<DailyClassGetModel>?
    suspend fun deleteDailyClass(remoteErrorEmitter: RemoteErrorEmitter, Id: Int): DailyClassGetModel?
    suspend fun updateDailyClass(remoteErrorEmitter: RemoteErrorEmitter, dailyClassEntity: DailyClassEntity): DailyClassGetModel?

}