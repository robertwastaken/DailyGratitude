package com.robert.dailygratitude.di

import com.robert.dailygratitude.data.DailyGratitudeDatabaseRepository
import com.robert.dailygratitude.data.DailyGratitudeInMemoryRepository
import com.robert.dailygratitude.data.DailyGratitudeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @InMemoryRepository
    @Binds
    abstract fun provideDailyGratitudeInMemoryRepository(inMemoryRepository: DailyGratitudeInMemoryRepository):
            DailyGratitudeRepository

    @DatabaseRepository
    @Binds
    abstract fun provideDailyGratitudeDatabaseRepository(databaseRepository: DailyGratitudeDatabaseRepository):
            DailyGratitudeRepository
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InMemoryRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseRepository