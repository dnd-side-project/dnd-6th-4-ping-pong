package com.dnd.sixth.lmsservice.data.repository.subject

import com.dnd.sixth.lmsservice.data.mapper.toEntity
import com.dnd.sixth.lmsservice.data.mapper.toModel
import com.dnd.sixth.lmsservice.data.repository.emitter.RemoteErrorEmitter
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class SubjectRepositoryImpl(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
    private val remoteErrorEmitter: RemoteErrorEmitter
) : SubjectRepository {

    override suspend fun createSubject(subjectEntity: SubjectEntity): SubjectEntity? =
        subjectRemoteDataSource.createSubject(remoteErrorEmitter, subjectEntity.toModel())?.toEntity()


    override suspend fun getGeneralClassList(uid: Int): List<GeneralSubjectEntity>? =
        subjectRemoteDataSource.getGeneralClassList(remoteErrorEmitter, uid)?.map {
            it.toEntity()
        }

    override suspend fun deleteSubject(subjectId: Int): SubjectEntity? =
        subjectRemoteDataSource.deleteSubject(remoteErrorEmitter, subjectId)?.toEntity()


    override suspend fun updateSubject(subjectEntity: SubjectEntity): SubjectEntity? =
        subjectRemoteDataSource.updateSubject(remoteErrorEmitter, subjectEntity.toModel())?.toEntity()

    override suspend fun getSubject(subjectId: Int): SubjectEntity? =
        subjectRemoteDataSource.getSubject(remoteErrorEmitter, subjectId)?.toEntity()

    override suspend fun getAllSubjectList(): List<SubjectEntity> =
        subjectRemoteDataSource.getAllSubjectList(remoteErrorEmitter).map { it.toEntity() }


}