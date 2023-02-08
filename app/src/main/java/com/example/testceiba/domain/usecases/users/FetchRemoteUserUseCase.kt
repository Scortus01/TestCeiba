package com.example.testceiba.domain.usecases.users

import com.example.testceiba.data.model.entity.UserEntity
import com.example.testceiba.domain.repository.UserRepository
import javax.inject.Inject

class FetchRemoteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): List<UserEntity> =
        repository.fetchAPIUsers().map { it.mapperEntity() }
}