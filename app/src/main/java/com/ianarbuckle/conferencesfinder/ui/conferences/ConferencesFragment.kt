package com.ianarbuckle.conferencesfinder.ui.conferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ianarbuckle.conferencesfinder.ConferencesApp
import com.ianarbuckle.conferencesfinder.ui.conferences.di.ConferencesModule
import com.ianarbuckle.conferencesfinder.ui.conferences.di.DaggerConferencesComponent
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferencesView
import com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel.ConferencesViewModel
import javax.inject.Inject

class ConferencesFragment : Fragment() {

    @Inject
    lateinit var view: ConferencesView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ConferencesViewModel> {
        viewModelFactory
    }

    companion object {
        fun newInstance() = ConferencesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerConferencesComponent.builder()
            .appComponent(ConferencesApp.component)
            .conferencesModule(ConferencesModule(this))
            .build()
            .inject(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.conferencesObservable.observe(viewLifecycleOwner, Observer {
            when {
                it.showProgress -> view.showLoading()
                it.showError -> view.showError()
                !it.showSuccess.isNullOrEmpty() -> {
                    view.hideLoading()
                    view.initAdapter(it.showSuccess)
                }
            }
        })
    }

}
