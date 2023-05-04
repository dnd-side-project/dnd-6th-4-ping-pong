package com.dnd.sixth.lmsservice.domain.useCase.subject

import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class UpdateSubjectUseCase(private val subjectRepository: SubjectRepository) {
    suspend operator fun invoke(subjectEntity: SubjectEntity): SubjectEntity? {
        return subjectRepository.createSubject(subjectEntity)
    }
}