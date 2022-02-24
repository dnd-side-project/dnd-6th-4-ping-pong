package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.domain.useCase.*
import org.koin.dsl.module

val useCaseModules = module {

    factory {
        CreateSubjectUseCase(get())
    }
    factory {
        GetGeneralSubjectListUseCase(get())
    }
    factory {
        DeleteSubjectUseCase(get())
    }
    factory {
        UpdateSubjectUseCase(get())
    }
    factory{
        GetDailyClassUseCase(get())
    }
}