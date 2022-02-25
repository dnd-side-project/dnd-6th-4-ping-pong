package com.dnd.sixth.lmsservice.di.modules


import com.dnd.sixth.lmsservice.domain.useCase.*

import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.CreateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.DeleteDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.GetDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.UpdateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.UpdateFeedBackUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.CreateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.UpdateSubjectUseCase

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

    /*
    타임라인 리스트
     */
    factory{
        GetDailyClassUseCase(get())
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

    /*
   피드백 코멘트
    */
    factory {
        UpdateFeedBackUseCase(get())
    }

}