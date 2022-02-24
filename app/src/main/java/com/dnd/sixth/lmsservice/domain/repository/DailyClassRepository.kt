package com.dnd.sixth.lmsservice.domain.repository

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity

interface DailyClassRepository {
    /*
    *  캘린더에서 수업 일정 추가
    * */
    suspend fun createDailyClass(dailyClass: DailyEntity): DailyEntity?


    /*
   *  캘린더에서 수업 일정 리스트 가져오기
   * */
    suspend fun getDailyClassList(uid: Int): List<DailyEntity>?


    /*
    *  캘린더에서 수업 일정 삭제
    * */
    suspend fun deleteDailyClass(classId: Int): DailyEntity?


    /*
    *  캘린더에서 수업 일정 업데이트
    * */
    suspend fun updateDailyClass(dailyClass: DailyEntity): DailyEntity?

}