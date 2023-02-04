package com.example.testceiba.domain.usecases.users

import com.example.testceiba.domain.model.UserView
import com.example.testceiba.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class FetchLocalUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): Flow<List<UserView>> =
        repository.fetchLocalUsers().flatMapLatest {
            flow {
                emit(it.map { it.mapper() })
            }
        }
}