package com.fsm.superpomodoro

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class PomodoroApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger()
            androidContext(this@PomodoroApp)
            modules(module)
        }
    }
}