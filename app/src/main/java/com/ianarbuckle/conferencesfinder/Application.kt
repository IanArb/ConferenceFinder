package com.ianarbuckle.conferencesfinder

import android.app.Application
import com.ianarbuckle.conferencesfinder.di.AppComponent
import com.ianarbuckle.conferencesfinder.di.AppModule
import com.ianarbuckle.conferencesfinder.di.DaggerAppComponent

class ConferencesApp : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

        component.inject(this)
    }
}