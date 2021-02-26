package com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianarbuckle.conferencesfinder.ui.conferences.model.UIViewState
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.utils.asLiveData
import conferences.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ConferencesViewModel @Inject constructor(private val useCase: ConferencesUseCase) : ViewModel() {

    private val mutableConferencesData = MutableLiveData<Any>()

    val conferencesObservable = mutableConferencesData.asLiveData()

    init {
        emitUIState(UIViewState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.conferences()

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUIState(UIViewState.Success(result.data))
                } else {
                    emitUIState(UIViewState.Error)
                }
            }
        }
    }

    private fun emitUIState(uiState: UIViewState<Any>) {
        mutableConferencesData.postValue(uiState)
    }
}
