package com.example.testceiba.di

import com.example.testceiba.data.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerQuoteApiClient(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}

private const val URL = "https://jsonplaceholder.typicode.com/"