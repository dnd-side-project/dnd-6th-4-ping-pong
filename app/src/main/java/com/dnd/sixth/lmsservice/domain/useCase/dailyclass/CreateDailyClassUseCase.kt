package com.dnd.sixth.lmsservice.domain.useCase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class CreateDailyClassUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(dailyEntity: DailyEntity): DailyEntity? {
        return dailyClassRepository.createDailyClass(dailyEntity)
    }
}