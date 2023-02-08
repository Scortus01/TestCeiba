package com.example.testceiba.di

import com.example.testceiba.data.dao.UserDAO
import com.example.testceiba.data.network.PublicationService
import com.example.testceiba.data.network.UserService
import com.example.testceiba.data.repository.PublicationRepositoryImp
import com.example.testceiba.data.repository.UserRepositoryImp
import com.example.testceiba.domain.repository.PublicationsRepository
import com.example.testceiba.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providerUserRepository(
        userService: UserService,
        userDAO: UserDAO
    ): UserRepository {
        return UserRepositoryImp(
            userService = userService,
            userDAO = userDAO
        )
    }

    @Singleton
    @Provides
    fun providerPublicationRepository(
        publicationService: PublicationService
    ): PublicationsRepository{
        return PublicationRepositoryImp(
            publicationService = publicationService
        )
    }
}
