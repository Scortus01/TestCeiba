package com.example.testceiba.domain.repository

import com.example.testceiba.data.model.entity.UserEntity
import com.example.testceiba.data.model.server.ApiUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchAPIUsers(): List<ApiUser>

    fun fetchLocalUsers(): Flow<List<UserEntity>>

    suspend fun saveAllUsers(users: List<UserEntity>)
}