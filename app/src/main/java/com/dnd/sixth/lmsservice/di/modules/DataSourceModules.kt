package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.repository.subject.datasource.remote.SubjectRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModules = module {

    factory {
        SubjectRemoteDataSourceImpl(get())
    }
}