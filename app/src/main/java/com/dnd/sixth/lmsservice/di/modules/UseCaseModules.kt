package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.domain.useCase.MakeSubjectUseCase
import org.koin.dsl.module

val useCaseModules = module {

    single {
        MakeSubjectUseCase(get())
    }
}