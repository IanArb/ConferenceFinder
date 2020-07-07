package com.ianarbuckle.conferencesfinder.ui.conferences.di

import android.content.Context
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferencesView
import conferences.repository.ConferencesRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ConferencesModule {

    @Provides
    fun provideRepository(): ConferencesRepository = ConferencesRepository()

    @Provides
    fun provideView(@ActivityContext context: Context): ConferencesView = ConferencesView(context)
}