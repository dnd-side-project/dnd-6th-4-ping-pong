package com.dnd.sixth.lmsservice.domain.usecase

import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassTimeLineRepository

/*
*  타임라인 같은 경우 해당 과목의 수업(DailyClass)리스트를 가져오는 Usecase
*  invoke(@param) id : subjectID
* */
class GetDailyClassTimeLineUseCase (private val dailyClassTimeLineRepository: DailyClassTimeLineRepository) {
    suspend operator fun invoke(subjectId: Int): List<DailyClassEntity>? {
        return dailyClassTimeLineRepository.getDailyClassList(subjectId)
    }
}