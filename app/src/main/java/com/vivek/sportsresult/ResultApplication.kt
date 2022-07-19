package com.vivek.sportsresult

import android.app.Application
import com.vivek.sportsresult.di.appModule
import com.vivek.sportsresult.di.networkModule
import com.vivek.sportsresult.di.repositoryModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ResultApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjection()
    }

    private fun initializeDependencyInjection() {
        startKoin {
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}