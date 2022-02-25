package com.dnd.sixth.lmsservice.domain.usecase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class GetDailyClassUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(uid: Int, subjectId: Int): DailyEntity =
        dailyClassRepository.getDailyClass(uid, subjectId)
}