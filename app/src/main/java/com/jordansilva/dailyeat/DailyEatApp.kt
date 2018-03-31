package com.jordansilva.dailyeat

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.LevelStartEvent
import io.fabric.sdk.android.Fabric

class DailyEatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics(), Answers())
    }

}