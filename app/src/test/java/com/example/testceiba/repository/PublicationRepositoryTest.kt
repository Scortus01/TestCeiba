package com.example.testceiba.repository

import com.example.testceiba.data.model.server.ApiPublication
import com.example.testceiba.data.network.PublicationService
import com.example.testceiba.data.repository.PublicationRepositoryImp
import com.example.testceiba.domain.repository.PublicationsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PublicationRepositoryTest {

    private val publicationService: PublicationService = mockk()

    private lateinit var publicationsRepository: PublicationsRepository

    @Before
    fun setup(){
        publicationsRepository = PublicationRepositoryImp(
            publicationService = publicationService
        )
    }

    @Test
    fun `when fetchAPIPublications is called then should returns list of publications`(){
        coEvery {
            publicationService.getListPublication(userId)
        } answers {
            apiPublication
        }

        val response = runBlocking {
            publicationsRepository.fetchRemotePublications(userId)
        }

        coVerify(exactly = 1) {
            publicationService.getListPublication(userId)
        }

        Assert.assertEquals(apiPublication, response)
    }

    private val userId = 1
    private val publicationId = 1
    private val apiPublication = listOf(
        ApiPublication(
            userId,
            publicationId,
            "Default title",
            "Default body"
        )
    )
}