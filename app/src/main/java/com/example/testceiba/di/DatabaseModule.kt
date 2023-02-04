package com.example.testceiba.di

import android.content.Context
import androidx.room.Room
import com.example.testceiba.data.dao.UserDAO
import com.example.testceiba.data.database.TestDatabase
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
    fun provideUserDAO(testDatabase: TestDatabase): UserDAO = testDatabase.userDAO()

    @Provides
    @Singleton
    fun providerMovieDatabase(@ApplicationContext appContext: Context): TestDatabase =
        Room.databaseBuilder(appContext, TestDatabase::class.java, DATABASE_NAME).build()
}

private const val DATABASE_NAME = "Database.db"