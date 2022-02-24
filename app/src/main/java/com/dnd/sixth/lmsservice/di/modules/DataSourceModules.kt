package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.dailyclass.datasource.remote.DailyClassRemoteDataSourceImpl
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSourceImpl
import com.dnd.sixth.lmsservice.data.repository.user.local.UserLocalDataSource
import com.dnd.sixth.lmsservice.data.repository.user.local.UserLocalDataSourceImpl
import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSource
import com.dnd.sixth.lmsservice.data.repository.user.remote.UserRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModules = module {

    single<SubjectRemoteDataSource> {
        SubjectRemoteDataSourceImpl(get())
    }
    single<DailyClassRemoteDataSource> {
        DailyClassRemoteDataSourceImpl(get())
    }
    single<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(get())
    }
    single<UserLocalDataSource> {
        UserLocalDataSourceImpl(get())
    }

}