package com.danish.movieapp

import android.app.Application
import com.danish.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(appModules)
        }
    }
}