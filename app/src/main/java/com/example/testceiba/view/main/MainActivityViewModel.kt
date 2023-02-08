package com.example.testceiba.view.main

import androidx.lifecycle.viewModelScope
import com.example.testceiba.base.BaseViewModel
import com.example.testceiba.domain.model.UserView
import com.example.testceiba.domain.usecases.users.FetchLocalUserUseCase
import com.example.testceiba.domain.usecases.users.FetchRemoteUserUseCase
import com.example.testceiba.domain.usecases.users.SaveUsersUseCase
import com.example.testceiba.utils.CoroutinesContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchLocalUserUseCase: FetchLocalUserUseCase,
    private val fetchRemoteUserUseCase: FetchRemoteUserUseCase,
    private val saveUsersUseCase: SaveUsersUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel<MainActivityViewModel.State, Nothing>(State()) {

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            withContext(coroutinesContextProvider.io) {
                fetchLocalUserUseCase()
                    .catch { exception ->
                        println(exception)
                    }
                    .collect { users ->
                        if (users.isEmpty()) {
                            getUsersRemote()
                            return@collect
                        }

                        updateState {
                            it.copy(
                                users = users,
                                originalUsers = users
                            )
                        }
                    }
            }
        }
    }

    fun filterUsers(query: String) {
        viewModelScope.launch {
            val users = currentState().originalUsers.filter { it.userName.contains(query, true) }
            updateState {
                it.copy(users = users)
            }
        }
    }

    private fun getUsersRemote() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            withContext(coroutinesContextProvider.io) {
                try {
                    val usersResponse = fetchRemoteUserUseCase()
                    saveUsersUseCase(usersResponse)
                } catch (ex: Exception) {
                    print(ex)
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val users: List<UserView> = emptyList(),
        val originalUsers: List<UserView> = emptyList()
    )
}