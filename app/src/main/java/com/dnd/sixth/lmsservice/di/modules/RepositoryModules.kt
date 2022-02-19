package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModules = module {

    //SharedPreference
    single { PreferenceManager(androidContext()) }

}