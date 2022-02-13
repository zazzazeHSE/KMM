package com.example.transportschedule.android

import android.app.Application
import com.example.weatherapp.BuildConfig
import com.example.transportschedule.koin.commonModule
import com.example.transportschedule.koin.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module

public class ScheduleApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ScheduleApp)
            module { commonModule }
        }
    }
}
