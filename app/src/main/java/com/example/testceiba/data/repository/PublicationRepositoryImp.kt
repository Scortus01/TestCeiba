package com.example.testceiba.data.repository

import com.example.testceiba.data.model.server.ApiPublication
import com.example.testceiba.data.network.PublicationService
import com.example.testceiba.domain.repository.PublicationsRepository
import javax.inject.Inject

class PublicationRepositoryImp @Inject constructor(
    private val publicationService: PublicationService
): PublicationsRepository {

    override suspend fun fetchRemotePublications(userId: Int): List<ApiPublication> = publicationService.getListPublication(userId)

}