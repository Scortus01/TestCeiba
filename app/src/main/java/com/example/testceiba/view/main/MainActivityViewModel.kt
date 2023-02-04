package com.example.testceiba.view.main

import androidx.lifecycle.viewModelScope
import com.example.testceiba.base.BaseViewModel
import com.example.testceiba.domain.model.UserView
import com.example.testceiba.domain.usecases.users.FetchLocalUserUseCase
import com.example.testceiba.utils.CoroutinesContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchLocalUserUseCase: FetchLocalUserUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel<MainActivityViewModel.State, Nothing>(State()) {

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            withContext(coroutinesContextProvider.io) {
                val usersResponse = fetchLocalUserUseCase()
                    .catch { exception ->
                        println(exception)
                    }
                    .onStart {
                        updateState { it.copy(isLoading = true) }
                    }
                    .collect { users ->
                        if (users.isEmpty()) {
                            return@collect
                        }

                        updateState {
                            it.copy(users = users)
                        }
                    }
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val users: List<UserView> = emptyList()
    )
}