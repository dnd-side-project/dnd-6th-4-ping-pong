package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.CreateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.DeleteDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.UpdateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.CreateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.UpdateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.ChangePasswordUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.ChangeUserNameUseCase
import org.koin.dsl.module

val useCaseModules = module {

    /* Subject / General Subject*/
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


    /* Daily Class */
    factory {
        CreateDailyClassUseCase(get())
    }
    factory {
        DeleteDailyClassUseCase(get())
    }
    factory {
        GetDailyClassListUseCase(get())
    }
    factory {
        UpdateDailyClassUseCase(get())
    }


    /* User */
    factory {
        ChangeUserNameUseCase(get())
    }
    factory {
        ChangePasswordUseCase(get())
    }
}