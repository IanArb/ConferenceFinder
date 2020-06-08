package com.ianarbuckle.conferencesfinder.ui.conferences.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.ui.conferences.view.adapter.ConferencesAdapter
import conferences.model.Conference
import kotlinx.android.synthetic.main.main_fragment.view.*

class ConferencesView constructor(context: Context, attrs: AttributeSet ? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.main_fragment, this)

        toolbar.apply {
            title = "Conferences"
            setTitleTextColor(R.color.colorBlack)
        }
    }

    fun initAdapter(conferences: List<Conference>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ConferencesAdapter(conferences)
        }
    }

    fun showLoading() {
        progressBar.isVisible
    }

    fun hideLoading() {
        progressBar.isGone
    }

    fun showError() {
        Toast.makeText(context, "Error processing request", Toast.LENGTH_SHORT).show()
    }


}