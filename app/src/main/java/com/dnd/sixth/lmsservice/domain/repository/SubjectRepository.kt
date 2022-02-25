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
    * 데이터가 통합된 수업 리스트 가져오기
    *  @return : List<GeneralSubjectEntity>
    * */
    suspend fun getGeneralClassList(uid: Int): List<GeneralSubjectEntity>?

    /*
    * 수업 지우기
    * */
    suspend fun deleteSubject(subjectId: Int): SubjectEntity?

    /*
    *  수업 데이터 업데이트
    * */
    suspend fun updateSubject(subjectEntity: SubjectEntity): SubjectEntity?

    /*
    * 수업 가져오기
    * */
    suspend fun getSubject(subjectId: Int): SubjectEntity?

    /*
    *  DB에 저장되어 있는 모든 수업 리스트 가져오기
    * */
    suspend fun getAllSubjectList(): List<SubjectEntity>
}