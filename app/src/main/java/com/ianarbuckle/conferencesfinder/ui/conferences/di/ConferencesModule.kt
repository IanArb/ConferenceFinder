package com.ianarbuckle.conferencesfinder.ui.conferences.di

import conferences.repository.ConferencesRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConferencesModule {

    @Provides
    fun provideRepository(): ConferencesRepository = ConferencesRepository()
}