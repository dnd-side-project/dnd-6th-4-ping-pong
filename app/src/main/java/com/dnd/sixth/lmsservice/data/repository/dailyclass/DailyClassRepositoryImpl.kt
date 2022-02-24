package com.dnd.sixth.lmsservice.data.repository.dailyclass

import android.util.Log
import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository


class DailyClassRepositoryImpl(
    private val dailyClassRemoteDataSource: DailyClassRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
) : DailyClassRepository {

    //데일리 클래스 생성
    override suspend fun createDailyClass(dailyClassEntity: DailyClassEntity): DailyClassEntity? =
        dailyClassRemoteDataSource.createDailyClass(remoteErrorEmitter, dailyClassEntity)?.toEntity()


    //데일리 클래스 불러오기 (타임라인에 사용)
    override suspend fun getDailyClassList(subjectId: Int): List<DailyClassEntity>? =
        dailyClassRemoteDataSource.getDailyClassList(remoteErrorEmitter, subjectId)?.map {
            it.toEntity()
        }

    //데일리 클래스 삭제
    override suspend fun deleteDailyClass(Id: Int): DailyClassEntity? =
        dailyClassRemoteDataSource.deleteDailyClass(remoteErrorEmitter, Id)?.toEntity()


    //데일리 클래스 업데이트
    override suspend fun updateDailyClass(dailyClassEntity: DailyClassEntity): DailyClassEntity? =
        dailyClassRemoteDataSource.updateDailyClass(remoteErrorEmitter, dailyClassEntity)?.toEntity()
}