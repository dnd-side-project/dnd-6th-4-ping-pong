package com.dnd.sixth.lmsservice.domain.repository

import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity

interface DailyClassTimeLineRepository {

    /*
    *  DailyClassEntity 수업 리스트 가져오기
    *
    * */
    suspend fun getDailyClassList(subjectId : Int): List<DailyClassEntity>?

    /*
   * DailyClassEntity 수업 생성
   * */
    suspend fun createDailyClass(dailyEntity: DailyClassEntity): DailyClassEntity?

    /*
    * DailyClassEntity 수업 지우기
    * */
    suspend fun deleteDailyClass(Id: Int): DailyClassEntity?

    /*
    *  DailyClassEntity 수업 데이터 업데이트
    * */
    suspend fun updateDailyClass(dailyEntity: DailyClassEntity): DailyClassEntity?
}