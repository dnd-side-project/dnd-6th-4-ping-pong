package com.dnd.sixth.lmsservice.domain.usecase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class GetDailyClassUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(dailyId: Int, subjectId: Int): DailyEntity =
        dailyClassRepository.getDailyClass(dailyId, subjectId)
}