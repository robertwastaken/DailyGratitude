package com.robert.dailygratitude.di

import android.content.Context
import androidx.room.Room
import com.robert.dailygratitude.db.EntriesDatabase
import com.robert.dailygratitude.db.EntryCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesEntriesDatabase(
        @ApplicationContext context: Context,
    ): EntriesDatabase =
        Room.databaseBuilder(
            context,
            EntriesDatabase::class.java,
            "entries",
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesEntryCardDao(
        database: EntriesDatabase,
    ): EntryCardDao = database.entryCardDao()
}