package com.dnd.sixth.lmsservice.domain.repository

import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity

interface SubjectRepository {
    /*
    * 수업 생성
    * @return : 생성 성공 여부
    * */
    suspend fun makeSubject(subjectEntity: SubjectEntity): SubjectEntity?
}