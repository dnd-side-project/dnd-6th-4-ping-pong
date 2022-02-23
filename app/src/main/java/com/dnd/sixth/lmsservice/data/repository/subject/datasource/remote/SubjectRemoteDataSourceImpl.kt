package com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote

import com.dnd.sixth.lmsservice.data.network.api.SubjectApi
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity

class SubjectRemoteDataSourceImpl(private val subjectApi: SubjectApi) : SubjectRemoteDataSource {
    // 수업 생성
    override suspend fun makeSubject(
        remoteErrorEmitter: RemoteErrorEmitter,
        subjectEntity: SubjectEntity
    ): Boolean? {
        return safeApiCall(remoteErrorEmitter) {
            subjectApi.makeSubject(
                subjectEntity.subjectName,
                subjectEntity.monthlyCnt,
                subjectEntity.classTime,
                subjectEntity.classDay,
                subjectEntity.teacherId,
                subjectEntity.color,
                subjectEntity.classDays,
            ).body()
        }!!
    }
}