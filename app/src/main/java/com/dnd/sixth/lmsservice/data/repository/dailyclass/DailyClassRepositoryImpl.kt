package com.dnd.sixth.lmsservice.data.repository.dailyclass

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.mapper.toModel
import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class DailyClassRepositoryImpl(
    private val dailyClassRemoteDataSource: DailyClassRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
): DailyClassRepository {
    override suspend fun createDailyClass(dailyEntity: DailyEntity): DailyEntity? {
        return dailyClassRemoteDataSource.createDailyClass(remoteErrorEmitter, dailyEntity.toModel())?.toEntity()
    }

    override suspend fun getDailyClassList(uid: Int): List<DailyEntity>? {
        return dailyClassRemoteDataSource.getDailyClassList(remoteErrorEmitter, uid)?.map {
            it.toEntity()
        }
    }

    override suspend fun deleteDailyClass(dailyId: Int): DailyEntity? {
        return dailyClassRemoteDataSource.deleteDailyClass(remoteErrorEmitter, dailyId)?.toEntity()
    }

    override suspend fun updateDailyClass(dailyEntity: DailyEntity): DailyEntity? {
        return dailyClassRemoteDataSource.updateDailyClass(remoteErrorEmitter, dailyEntity.toModel())?.toEntity()
    }

    override suspend fun getDailyClass(dailyId: Int, subjectId: Int): DailyEntity {
        return dailyClassRemoteDataSource.getDailyClass(remoteErrorEmitter, dailyId, subjectId).toEntity()
    }

    override suspend fun getAllDailyClassList(): List<DailyEntity> {
        return dailyClassRemoteDataSource.getAllDailyClass(remoteErrorEmitter).map { it.toEntity() }
    }

}