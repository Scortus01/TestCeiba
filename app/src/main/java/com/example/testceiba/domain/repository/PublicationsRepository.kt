package com.example.testceiba.domain.repository

import com.example.testceiba.data.model.server.ApiPublication

interface PublicationsRepository {

    suspend fun fetchRemotePublications(userId: Int): List<ApiPublication>
}