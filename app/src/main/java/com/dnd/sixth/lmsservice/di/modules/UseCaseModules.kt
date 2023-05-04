package com.dnd.sixth.lmsservice.di.modules


import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.CreateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.DeleteDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.dailyclass.UpdateDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.GetCommentFromDailyUseCase
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.GetFeedBackUseCaseFromDaily
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.UpdateFeedBackUseCase
import com.dnd.sixth.lmsservice.domain.useCase.feedbackComment.WriteCommentUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.comment.ChangeCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.comment.GetCommentPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.feedback.ChangeFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.feedback.GetFeedbackPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.start.ChangeStartPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.push.start.GetStartPushUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.CreateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.subject.UpdateSubjectUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.GetLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.SaveLocalContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.contact.SaveRemoteContactTimeUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.GetLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.SaveLocalMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.myself.SaveRemoteMyNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.GetLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.SaveLocalParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.number.parent.SaveRemoteParentNumberUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.password.ChangePasswordUseCase
import com.dnd.sixth.lmsservice.domain.useCase.user.username.ChangeUserNameUseCase
import com.dnd.sixth.lmsservice.domain.usecase.GetDailyClassTimeLineUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetAllDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.dailyclass.GetDailyClassUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.DeleteSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetAllSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetGeneralSubjectListUseCase
import com.dnd.sixth.lmsservice.domain.usecase.subject.GetSubjectUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.GetUserByEmailUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.GetUserByUidUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.profile.GetLocalProfileUriUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.profile.SaveLocalProfileUriUseCase
import com.dnd.sixth.lmsservice.domain.usecase.user.profile.SaveRemoteProfileUriUseCase
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
    factory {
        GetSubjectUseCase(get())
    }
    factory {
        GetAllSubjectListUseCase(get())
    }

    /*
    타임라인 리스트
     */

    factory {
        GetDailyClassTimeLineUseCase(get())
    }
    factory {
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
    factory {
        GetAllDailyClassListUseCase(get())
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
    factory {
        SaveRemoteProfileUriUseCase(get())
    }
    factory {
        SaveLocalProfileUriUseCase(get())
    }
    factory {
        GetLocalProfileUriUseCase(get())
    }
    factory {
        GetUserByEmailUseCase(get())
    }
    factory {
        GetUserByUidUseCase(get())
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

    factory {
        GetFeedBackUseCaseFromDaily(get()) //피드백 내용 불러오기
    }

    factory{
        WriteCommentUseCase(get()) //코멘트 작성하기
    }

    factory{
        GetCommentFromDailyUseCase(get()) //코멘트 내용 불러오기
    }

}