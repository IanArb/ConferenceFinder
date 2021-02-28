package com.ianarbuckle.conferencesfinder.ui.conferences.model

import conferences.model.Conference

data class ConferencesUiData(
    val data: List<Conference>? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)