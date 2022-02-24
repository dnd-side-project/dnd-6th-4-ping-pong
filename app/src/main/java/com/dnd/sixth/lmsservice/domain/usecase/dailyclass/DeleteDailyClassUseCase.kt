package com.dnd.sixth.lmsservice.domain.usecase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class DeleteDailyClassUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(classId: Int): DailyEntity? {
        return dailyClassRepository.deleteDailyClass(classId)
    }
}