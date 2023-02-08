package com.example.testceiba.domain.usecases.publications

import com.example.testceiba.domain.model.PublicationView
import com.example.testceiba.domain.repository.PublicationsRepository
import javax.inject.Inject

class FetchRemotePublicationsUseCase @Inject constructor(
    private val repository: PublicationsRepository
) {

    suspend operator fun invoke(userId: Int): List<PublicationView> =
        repository.fetchRemotePublications(userId).map { it.mapper() }
}