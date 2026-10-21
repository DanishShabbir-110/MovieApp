package com.danish.movieapp

import android.app.Application
import com.danish.di.appModules
import com.o3interfaces.fusioninspect.core.Fusion
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(appModules)
        }

        val clientSecret="88ae7b0fe7614072ba47107fefd8536c"
        val projectToken="3D8E3FD78B7F4B9A8CEB3476F2F47862"
        Fusion.Builder(app=this,clientSecret,projectToken).build()
    }
}