package com.dnd.sixth.lmsservice

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.dnd.sixth.lmsservice.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    val context: Context
        get() = this

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModules,
                    repositoryModules,
                    networkModules,
                    useCaseModules,
                    dataSourceModules,
                )
            )

        }
    }


    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App
            private set
    }

}
