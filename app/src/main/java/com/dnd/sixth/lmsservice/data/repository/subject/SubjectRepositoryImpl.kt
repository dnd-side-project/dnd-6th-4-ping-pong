package com.dnd.sixth.lmsservice.data.repository.subject

import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class SubjectRepositoryImpl(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
): SubjectRepository {
    override suspend fun makeSubject(subjectEntity: SubjectEntity): Boolean {
        return subjectRemoteDataSource.makeSubject(remoteErrorEmitter, subjectEntity)
    }
}