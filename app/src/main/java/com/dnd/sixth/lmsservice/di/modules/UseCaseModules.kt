package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.CreateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.DeleteDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.UpdateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.comment.ChangeCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.comment.GetCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.feedback.ChangeFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.feedback.GetFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.start.ChangeStartPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.push.start.GetStartPushUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.CreateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.UpdateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.contact.GetLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.contact.SaveLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.contact.SaveRemoteContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.myself.GetLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.myself.SaveLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.myself.SaveRemoteMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.parent.GetLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.parent.SaveLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.number.parent.SaveRemoteParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.password.ChangePasswordUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.username.ChangeUserNameUseCase
import com.dnd.sixth.lmsservice.domain.useCase.*
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.UpdateFeedBackUseCase
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


    /* User */
    factory {
        ChangeUserNameUseCase(get())
    }
    factory {
        ChangePasswordUseCase(get())
    }
    factory {
        SaveRemoteContactTimeUseCase(get())
    }
    factory {
        SaveLocalContactTimeUseCase(get())
    }
    factory {
        GetLocalContactTimeUseCase(get())
    }
    factory {
        GetLocalMyNumberUseCase(get())
    }
    factory {
        SaveLocalMyNumberUseCase(get())
    }
    factory {
        SaveRemoteMyNumberUseCase(get())
    }
    factory {
        GetLocalParentNumberUseCase(get())
    }
    factory {
        SaveLocalParentNumberUseCase(get())
    }
    factory {
        SaveRemoteParentNumberUseCase(get())
    }


    /* Push */
    factory {
        ChangeStartPushUseCase(get())
    }
    factory {
        GetStartPushUseCase(get())
    }
    factory {
        ChangeFeedbackPushUseCase(get())
    }
    factory {
        GetFeedbackPushUseCase(get())
    }
    factory {
        ChangeCommentPushUseCase(get())
    }
    factory {
        GetCommentPushUseCase(get())
    }
    
    /*
   피드백 코멘트
    */
    factory {
        UpdateFeedBackUseCase(get())
    }

}