package com.dnd.sixth.lmsservice.domain.usecase.dailyclass

import com.dnd.sixth.lmsservice.domain.entity.DailyEntity
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository

class GetDailyClassListUseCase(private val dailyClassRepository: DailyClassRepository) {
    suspend operator fun invoke(uid: Int): List<DailyEntity>? {
        return dailyClassRepository.getDailyClassList(uid)
    }
}