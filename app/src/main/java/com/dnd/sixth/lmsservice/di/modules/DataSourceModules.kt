package com.dnd.sixth.lmsservice.di.modules


import com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote.FeedBackCommentRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.FeedBackComment.datasource.remote.FeedBackCommentRemoteDataSourceImpl
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineTimeLineRemoteDataSourceImpl

import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSourceImpl

import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModules = module {

    single<SubjectRemoteDataSource> {
        SubjectRemoteDataSourceImpl(get())
    }


    single <DailyClassTimeLineRemoteDataSource> {
        DailyClassTimeLineTimeLineRemoteDataSourceImpl(get())
    }

    single<DailyClassRemoteDataSource> {
        DailyClassRemoteDataSourceImpl(get())
    }
    single<FeedBackCommentRemoteDataSource>{
        FeedBackCommentRemoteDataSourceImpl(get())

    }
}