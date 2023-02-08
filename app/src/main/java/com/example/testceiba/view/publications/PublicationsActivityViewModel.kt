package com.example.testceiba.view.publications

import androidx.lifecycle.viewModelScope
import com.example.testceiba.base.BaseViewModel
import com.example.testceiba.domain.model.PublicationView
import com.example.testceiba.domain.usecases.publications.FetchRemotePublicationsUseCase
import com.example.testceiba.utils.CoroutinesContextProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PublicationsActivityViewModel @AssistedInject constructor(
    @Assisted private val userId: Int,
    private val fetchRemotePublicationsUseCase: FetchRemotePublicationsUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider
): BaseViewModel<PublicationsActivityViewModel.State, Nothing>(State()) {

    init {
        getPublicationRemote()
    }

    private fun getPublicationRemote(){
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            withContext(coroutinesContextProvider.io){
                try {
                    val publications = fetchRemotePublicationsUseCase(userId)
                    updateState {
                        it.copy(publications = publications)
                    }
                }catch (ex: Exception){
                    print(ex)
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val publications: List<PublicationView> = emptyList(),
        val publicationsFilter: List<PublicationView> = emptyList()
    )

    @AssistedFactory
    interface PublicationsActivityViewModelFactory{
        fun create(userId: Int): PublicationsActivityViewModel
    }
}