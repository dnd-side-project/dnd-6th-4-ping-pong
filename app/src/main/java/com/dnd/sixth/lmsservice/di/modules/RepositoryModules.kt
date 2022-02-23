package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.repository.subject.SubjectRepositoryImpl
import com.dnd.sixth.lmsservice.domain.repository.SubjectRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModules = module {

    //SharedPreference
    single { PreferenceManager(androidContext()) }

    // Repositories
    single<SubjectRepository> {
        SubjectRepositoryImpl(get(), get())
    }
}