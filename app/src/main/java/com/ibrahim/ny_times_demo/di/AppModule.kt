package com.ibrahim.ny_times_demo.di

import android.app.Application
import com.ibrahim.ny_times_demo.util.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providePreferences(app : Application) : PreferencesManager = PreferencesManager(app)

}