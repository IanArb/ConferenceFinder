package com.ianarbuckle.conferencesfinder.ui.conferencedetail.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ianarbuckle.conferencesfinder.R
import conferences.model.Conference

class ConferenceDetailView constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): FrameLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.conference_detail_view, this)
    }

    fun showConference(conference: Conference) {

    }
}