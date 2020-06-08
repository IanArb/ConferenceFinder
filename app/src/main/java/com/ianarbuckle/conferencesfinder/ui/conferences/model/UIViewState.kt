package com.ianarbuckle.conferencesfinder.ui.conferences.model

import conferences.model.Conference

data class UIViewState(
    val showSuccess: List<Conference>?,
    val showProgress: Boolean,
    val showError: Boolean
)