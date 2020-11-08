package com.ianarbuckle.conferencesfinder.ui.conferencedetail.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianarbuckle.conferencesfinder.ui.conferences.model.UIViewState
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.utils.asLiveData
import conferences.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConferenceDetailViewModel @ViewModelInject constructor(private val useCase: ConferencesUseCase): ViewModel() {

    private val mutableUiState = MutableLiveData<Any>()

    val observeUiState = mutableUiState.asLiveData()

    fun init(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.fetchConferenceById(id)

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUiState(UIViewState.Success(result.data))
                } else {
                    emitUiState(UIViewState.Error)
                }
            }
        }
    }

    private fun emitUiState(uiState: UIViewState<Any>) {
        mutableUiState.postValue(uiState)
    }

}