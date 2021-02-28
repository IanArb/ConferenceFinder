package com.ianarbuckle.conferencesfinder.ui.conferencedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.model.ConferencesDetailUiData
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.utils.CoroutinesDispatcherProvider
import conferences.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ConferenceDetailViewModel @Inject constructor(private val useCase: ConferencesUseCase, private val dispatchers: CoroutinesDispatcherProvider): ViewModel() {

    private val _uiState = MutableStateFlow(ConferencesDetailUiData(isLoading = true))

    val uiState = _uiState.asStateFlow()

    fun init(id: String) {
        viewModelScope.launch(dispatchers.io) {
            val result = useCase.fetchConferenceById(id)

            withContext(dispatchers.main) {
                if (result is Result.Success) {
                    emitUiState(ConferencesDetailUiData(data = result.data))
                } else {
                    emitUiState(ConferencesDetailUiData(isError = true))
                }
            }
        }
    }

    private fun emitUiState(uiState: ConferencesDetailUiData) {
        _uiState.value = uiState
    }
}