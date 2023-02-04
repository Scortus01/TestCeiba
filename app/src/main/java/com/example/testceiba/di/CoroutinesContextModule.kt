package com.example.testceiba.di

import com.example.testceiba.utils.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesContextModule {

    @Provides
    fun provideContextProvider(): CoroutinesContextProvider =
        object : CoroutinesContextProvider {
            override val io: CoroutineContext
                get() = Dispatchers.IO
            override val main: CoroutineContext
                get() = Dispatchers.Main
        }
}