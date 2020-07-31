package com.ianarbuckle.conferencesfinder.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ianarbuckle.conferencesfinder.R

class ErrorView @JvmOverloads constructor(context: Context,
                                          attrs: AttributeSet? = null,
                                          defStyle: Int = 0): FrameLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.error_view, this)
    }

    fun tryAgainButtonCallback(callback: () -> Unit) {
        callback()
    }
}