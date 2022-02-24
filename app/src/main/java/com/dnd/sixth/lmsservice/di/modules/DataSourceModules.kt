package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.dailyclasstimelist.datasource.remote.DailyClassTimeLineTimeLineRemoteDataSourceImpl
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
}