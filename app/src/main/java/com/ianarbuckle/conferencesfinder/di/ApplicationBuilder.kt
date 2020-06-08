package com.ianarbuckle.conferencesfinder.di

import android.content.Context
import com.ianarbuckle.conferencesfinder.ConferencesApp
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Module
class AppModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideContext(): Context = context

}

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(conferencesApp: ConferencesApp)
}

