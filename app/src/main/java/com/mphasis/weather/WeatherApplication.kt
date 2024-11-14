package com.mphasis.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

// Marks the WeatherApplication class as an entry point for Hilt dependency injection
@HiltAndroidApp
class WeatherApplication : Application() {

    // Called when the application is starting, before any activity or service is created
    override fun onCreate() {
        super.onCreate()

        // Checks if the app is in debug mode to enable Timber logging
        if (BuildConfig.DEBUG) {
            // Plant a DebugTree for Timber, which logs debug information to Logcat
            Timber.plant(Timber.DebugTree())
        }
    }
}
