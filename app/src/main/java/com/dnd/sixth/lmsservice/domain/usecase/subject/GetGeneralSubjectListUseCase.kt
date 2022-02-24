package com.dnd.sixth.lmsservice.domain.usecase.subject

import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository

/*
*  GeneralSubjectList를 가져오는 Usecase
*  invoke(@param) uid : 유저 Uid
* */
class GetGeneralSubjectListUseCase(private val subjectRepository: SubjectRepository) {
    suspend operator fun invoke(uid: Int): List<GeneralSubjectEntity>? {
        return subjectRepository.getGeneralClassList(uid)
    }
}