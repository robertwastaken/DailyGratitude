package com.robert.dailygratitude.di

import com.robert.dailygratitude.data.DailyGratitudeInMemoryRepository
import com.robert.dailygratitude.data.DailyGratitudeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @InMemoryRepository
    @Provides
    fun provideDailyGratitudeInMemoryRepository(): DailyGratitudeRepository {
        return DailyGratitudeInMemoryRepository()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InMemoryRepository