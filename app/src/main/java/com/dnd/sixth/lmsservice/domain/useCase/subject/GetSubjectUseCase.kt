package com.dnd.sixth.lmsservice.domain.usecase.subject

import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

class GetSubjectUseCase(private val subjectRepository: SubjectRepository) {
    suspend operator fun invoke(subjectId: Int): SubjectEntity? {
        return subjectRepository.getSubject(subjectId)
    }
}