package com.ianarbuckle.conferencesfinder.ui.conferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ianarbuckle.conferencesfinder.BaseFragment
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferencesView
import com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel.ConferencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConferencesFragment : BaseFragment() {

    @Inject
    lateinit var view: ConferencesView

    private val viewModel: ConferencesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar(0)

        viewModel.init()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.conferencesObservable.observe(viewLifecycleOwner, Observer {
            when {
                it.showProgress -> view.showLoading()
                it.showError -> view.showError()
                !it.showSuccess.isNullOrEmpty() -> {
                    view.apply {
                        hideLoading()
                        initAdapter(it.showSuccess)
                        onItemClick()
                    }
                }
            }
        })
    }

}
