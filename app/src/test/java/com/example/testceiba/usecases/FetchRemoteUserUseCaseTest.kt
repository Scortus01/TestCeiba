package com.example.testceiba.usecases

import com.example.testceiba.data.model.entity.UserEntity
import com.example.testceiba.data.model.server.*
import com.example.testceiba.domain.repository.UserRepository
import com.example.testceiba.domain.usecases.users.FetchRemoteUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FetchRemoteUserUseCaseTest {

    private val repository: UserRepository = mockk()
    private val useCase = FetchRemoteUserUseCase(repository)

    @Test
    fun `test fetch remote user use case`() = runBlocking {

        coEvery {
            useCase.invoke()
        }answers {
            apiUsers
        }

        val response = runBlocking {
            repository.fetchAPIUsers().map { it.mapperEntity() }
        }

        coVerify(exactly = 1) {
            useCase.invoke()
        }

        Assert.assertEquals(apiUsers, response)
    }

    private val apiUsers = listOf(
        UserEntity(
            0,
            "",
            "",
            "",
            ""
        )
    )

}