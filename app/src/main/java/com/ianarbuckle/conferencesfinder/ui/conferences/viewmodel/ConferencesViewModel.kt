package com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel

import androidx.lifecycle.ViewModel
import com.ianarbuckle.conferencesfinder.ui.conferences.model.ConferencesUiData
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.utils.CoroutinesDispatcherProvider
import conferences.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ConferencesViewModel @Inject constructor(private val useCase: ConferencesUseCase, private val dispatcher: CoroutinesDispatcherProvider) : ViewModel() {

    private val _uiState = MutableStateFlow(ConferencesUiData(isLoading = true))

    val uiState = _uiState.asStateFlow()

    suspend fun init() {
        useCase.conferences()
            .flowOn(dispatcher.io)
            .collect {
                if (it is Result.Success) {
                    emitUIState(ConferencesUiData(data = it.data))
                } else {
                    emitUIState(ConferencesUiData(isError = true))
                }
            }
    }

    private fun emitUIState(uiState: ConferencesUiData) {
        _uiState.value = uiState
    }
}
