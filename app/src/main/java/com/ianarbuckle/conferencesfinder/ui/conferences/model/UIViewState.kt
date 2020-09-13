package com.ianarbuckle.conferencesfinder.ui.conferences.model

sealed class UIViewState<out T: Any> {
    data class Success<out T: Any>(val result: T): UIViewState<T>()
    object Error: UIViewState<Nothing>()
    object Loading: UIViewState<Nothing>()
}