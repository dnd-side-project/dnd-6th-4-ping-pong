package com.dnd.sixth.lmsservice.data.repository.subject

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class SubjectRepositoryImpl(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
): SubjectRepository {

    override suspend fun createSubject(subjectEntity: SubjectEntity): SubjectEntity? {
        return subjectRemoteDataSource.createSubject(remoteErrorEmitter, subjectEntity)?.toEntity()
    }

    override suspend fun getGeneralClassList(uid: Int): List<GeneralSubjectEntity>? {
        return subjectRemoteDataSource.getGeneralClassList(remoteErrorEmitter, uid)?.map {
            it.toEntity()
        }
    }
}