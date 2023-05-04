package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.repository.dailyclass.DailyClassRepositoryImpl
import com.dnd.sixth.lmsservice.data.repository.push.PushRepositoryImpl
import com.dnd.sixth.lmsservice.data.repository.subject.SubjectRepositoryImpl
import com.dnd.sixth.lmsservice.data.repository.user.UserRepositoryImpl
import com.dnd.sixth.lmsservice.domain.repository.DailyClassRepository
import com.dnd.sixth.lmsservice.domain.repository.PushRepository
import com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.FeedBackCommentRepositoryImpl
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.DailyClassTimeLineTimeLineRepositoryImpl
import com.dnd.sixth.lmsservice.domain.repository.DailyClassTimeLineRepository
import com.dnd.sixth.lmsservice.domain.repository.FeedBackCommentRepository
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository
import com.dnd.sixth.lmsservice.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModules = module {

    //SharedPreference
    single { PreferenceManager(androidContext()) }

    // Repositories
    single<SubjectRepository> {
        SubjectRepositoryImpl(get(), get())
    }


    single<DailyClassTimeLineRepository> {
        DailyClassTimeLineTimeLineRepositoryImpl(get(), get())
    }

    single<DailyClassRepository> {
        DailyClassRepositoryImpl(get(), get())
    }
    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }
    single<PushRepository> {
        PushRepositoryImpl(get())
    }
    single<FeedBackCommentRepository> {
        FeedBackCommentRepositoryImpl(get(),get())
    }
}