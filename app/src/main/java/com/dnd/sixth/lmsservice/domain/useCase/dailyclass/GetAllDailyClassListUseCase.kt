package com.dnd.sixth.lmsservice.domain.usecase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class GetAllDailyClassListUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(): List<DailyEntity> {
        return dailyClassRepository.getAllDailyClassList()
    }
}