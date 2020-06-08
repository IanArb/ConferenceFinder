package com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianarbuckle.conferencesfinder.ui.conferences.model.UIViewState
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.utils.asLiveData
import conferences.model.Conference
import conferences.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConferencesViewModel @Inject constructor(private val useCase: ConferencesUseCase) : ViewModel() {

    private val mutableConferencesData = MutableLiveData<UIViewState>()

    val conferencesObservable = mutableConferencesData.asLiveData()

    fun init() {
        emitUIState(showProgress = true)

        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.conferences()

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUIState(showSuccess =  result.data)
                } else {
                    emitUIState(showError = true)
                }
            }
        }
    }

    private fun emitUIState(showProgress: Boolean = false,
                    showSuccess: List<Conference>? = null,
                    showError: Boolean = false
    ) {
        val uiState = UIViewState(
            showSuccess,
            showProgress,
            showError
        )
        mutableConferencesData.postValue(uiState)
    }
}
