package com.example.testceiba.data.repository

import com.example.testceiba.data.dao.UserDAO
import com.example.testceiba.data.model.entity.UserEntity
import com.example.testceiba.data.model.server.ApiUser
import com.example.testceiba.data.network.UserService
import com.example.testceiba.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userService: UserService,
    private val userDAO: UserDAO
) : UserRepository {

    override suspend fun fetchAPIUsers(): List<ApiUser> = userService.getListUsers()

    override fun fetchLocalUsers(): Flow<List<UserEntity>> = userDAO.getUsers()

    override suspend fun saveAllUsers(users: List<UserEntity>) {
        userDAO.insertUsers(users)
    }
}