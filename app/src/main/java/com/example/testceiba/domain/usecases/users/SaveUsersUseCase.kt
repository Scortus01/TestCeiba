package com.example.testceiba.domain.usecases.users

import com.example.testceiba.data.model.entity.UserEntity
import com.example.testceiba.domain.repository.UserRepository
import javax.inject.Inject

class SaveUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(users: List<UserEntity>) {
        userRepository.saveAllUsers(users)
    }
}