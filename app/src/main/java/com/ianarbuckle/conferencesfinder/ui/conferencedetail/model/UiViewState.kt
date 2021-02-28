package com.ianarbuckle.conferencesfinder.ui.conferencedetail.model

import conferences.model.Conference

data class ConferencesDetailUiData(
    val data: Conference? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)