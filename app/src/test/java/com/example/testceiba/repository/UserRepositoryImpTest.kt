package com.example.testceiba.repository

import com.example.testceiba.data.dao.UserDAO
import com.example.testceiba.data.model.server.ApiAddress
import com.example.testceiba.data.model.server.ApiCompany
import com.example.testceiba.data.model.server.ApiGeo
import com.example.testceiba.data.model.server.ApiUser
import com.example.testceiba.data.network.UserService
import com.example.testceiba.data.repository.UserRepositoryImp
import com.example.testceiba.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private val userService: UserService = mockk()
    private val userDAO: UserDAO = mockk(relaxed = true)

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        userRepository = UserRepositoryImp(
            userService = userService,
            userDAO = userDAO
        )
    }

    @Test
    fun `when fetchAPIUsers is called then should returns list of users`() {
        coEvery {
            userService.getListUsers()
        } answers {
            apiUsers
        }

        val response = runBlocking {
            userRepository.fetchAPIUsers()
        }

        coVerify(exactly = 1) {
            userService.getListUsers()
        }

        Assert.assertEquals(apiUsers, response)
    }

    @Test
    fun `when fetchLocalUsers is called then should returns list of users`() {
        val fake = flow { emit( apiUsers.map { it.mapperEntity() } ) }

        coEvery {
            userDAO.getUsers()
        } answers {
            fake
        }

        val response = runBlocking {
            userRepository.fetchLocalUsers()
        }

        coVerify(exactly = 1) {
            userDAO.getUsers()
        }

        Assert.assertEquals(fake, response)
    }

    @Test
    fun `when saveAllUsers is called`(){

        val data = apiUsers.map { it.mapperEntity() }

        runBlocking {
            userDAO.insertUsers(data)
        }
        coVerify(exactly = 1) {
            userDAO.insertUsers(data)
        }
    }

}

private val apiUsers = listOf(
    ApiUser(
        0,
        "",
        "",
        "",
        ApiAddress(
            "",
            "",
            "",
            "",
            ApiGeo(
                "",
                ""
            )
        ),
        "",
        "",
        ApiCompany(
            "",
            "",
            ""
        )
    )
)