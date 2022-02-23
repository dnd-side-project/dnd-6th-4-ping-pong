package com.dnd.sixth.lmsservice.domain.useCase

import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class MakeSubjectUseCase(private val subjectRepository: SubjectRepository) {
    suspend operator fun invoke(subjectEntity: SubjectEntity): SubjectEntity? {
        return subjectRepository.makeSubject(subjectEntity)
    }
}