package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.domain.useCase.CreateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.GetGeneralSubjectListUseCase
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
}