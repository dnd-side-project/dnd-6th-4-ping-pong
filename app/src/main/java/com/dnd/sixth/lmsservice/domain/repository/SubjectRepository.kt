package com.dnd.sixth.lmsservice.domain.repository

import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity

interface SubjectRepository {
    /*
    * 수업 생성
    * @return : SubjectEntity
    * */
    suspend fun createSubject(subjectEntity: SubjectEntity): SubjectEntity?

    /*
    *  수업 리스트 가져오기
    *  @return : List<ClassEntity>
    * */
    suspend fun getGeneralClassList(uid: Int): List<GeneralSubjectEntity>?
}