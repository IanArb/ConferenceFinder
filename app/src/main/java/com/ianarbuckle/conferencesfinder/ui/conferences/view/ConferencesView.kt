package com.ianarbuckle.conferencesfinder.ui.conferences.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.ui.conferences.ConferencesFragmentDirections
import com.ianarbuckle.conferencesfinder.ui.conferences.view.adapter.ConferencesAdapter
import conferences.model.Conference
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlin.properties.Delegates

class ConferencesView @JvmOverloads constructor(context: Context, attrs: AttributeSet ? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

    private var conferencesAdapter: ConferencesAdapter by Delegates.notNull()

    init {
        inflate(context, R.layout.main_fragment, this)
    }

    fun initAdapter(conferences: List<Conference>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            conferencesAdapter = ConferencesAdapter(conferences)
            adapter = conferencesAdapter
        }
    }

    fun showLoading() {
        progressBar.isVisible = true
    }

    fun hideLoading() {
        progressBar.isGone = true
    }

    fun showError() {
        errorView.isVisible = true
    }

    fun hideError() {
        errorView.isGone = true
    }

    fun onTryAgain(callback: () -> Unit) {
        errorView.tryAgainButtonCallback {
            callback()
        }
    }

    fun onItemClick() {
        conferencesAdapter.onItemClickListener {
            val action = ConferencesFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }


}