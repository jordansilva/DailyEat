package com.jordansilva.dailyeat

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.jordansilva.dailyeat.di.KoinModule
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin

class DailyEatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics(), Answers())
        startKoin(this, listOf(KoinModule.AppModule,
                KoinModule.ViewModule,
                KoinModule.RepositoryModule,
                KoinModule.ApiModule))
    }

}