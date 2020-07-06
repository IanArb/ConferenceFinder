package com.ianarbuckle.conferencesfinder.ui.conferencedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ianarbuckle.conferencesfinder.BaseFragment
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.view.ConferenceDetailView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConferenceDetailFragment: BaseFragment() {

    private val args: ConferenceDetailFragmentArgs by navArgs()

    @Inject
    lateinit var view: ConferenceDetailView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar(0)
        initView()
    }

    private fun initView() {
        view.showConference(args.Conference)
    }
}