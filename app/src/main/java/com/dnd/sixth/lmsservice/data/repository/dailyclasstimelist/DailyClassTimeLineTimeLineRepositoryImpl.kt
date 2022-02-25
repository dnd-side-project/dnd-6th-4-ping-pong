package com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassTimeLineRepository


class DailyClassTimeLineTimeLineRepositoryImpl(
    private val dailyClassTimeLineRemoteDataSource: DailyClassTimeLineRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
) : DailyClassTimeLineRepository {

    //데일리 클래스 생성
    override suspend fun createDailyClass(dailyClassEntity: DailyClassEntity): DailyClassEntity? =
        dailyClassTimeLineRemoteDataSource.createDailyClass(remoteErrorEmitter, dailyClassEntity)?.toEntity()


    //데일리 클래스 불러오기 (타임라인에 사용)
    override suspend fun getDailyClassList(subjectId: Int): List<DailyClassEntity>? =
        dailyClassTimeLineRemoteDataSource.getDailyClassList(remoteErrorEmitter, subjectId)?.map {
            it.toEntity()
        }

    //데일리 클래스 삭제
    override suspend fun deleteDailyClass(Id: Int): DailyClassEntity? =
        dailyClassTimeLineRemoteDataSource.deleteDailyClass(remoteErrorEmitter, Id)?.toEntity()


    //데일리 클래스 업데이트
    override suspend fun updateDailyClass(dailyClassEntity: DailyClassEntity): DailyClassEntity? =
        dailyClassTimeLineRemoteDataSource.updateDailyClass(remoteErrorEmitter, dailyClassEntity)?.toEntity()
}