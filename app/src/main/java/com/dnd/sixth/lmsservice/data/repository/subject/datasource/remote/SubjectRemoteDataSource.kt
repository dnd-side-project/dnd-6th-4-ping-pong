package com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote

import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity

interface SubjectRemoteDataSource {
    suspend fun makeSubject(remoteErrorEmitter: RemoteErrorEmitter, subjectEntity: SubjectEntity): SubjectEntity?
}