//package com.ianarbuckle.conferencesfinder.ui.conferencedetail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.fragment.navArgs
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import com.google.android.material.appbar.AppBarLayout
//import com.ianarbuckle.conferencesfinder.BaseFragment
//import com.ianarbuckle.conferencesfinder.R
//import com.ianarbuckle.conferencesfinder.ui.conferencedetail.view.ConferenceDetailView
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class ConferenceDetailFragment: BaseFragment() {
//
//    private val args: ConferenceDetailFragmentArgs by navArgs()
//
//    @Inject
//    lateinit var view: ConferenceDetailView
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val appBar = requireActivity().findViewById<AppBarLayout>(R.id.appBar)
//        appBar.setExpanded(true)
//        initMap()
//        return view
//    }
//
//    private fun initMap() {
//        val mapFragment: SupportMapFragment = SupportMapFragment.newInstance()
//        mapFragment.getMapAsync {
//            val location = args.Conference.location
//            val venue = location.venue
//            val latitude = venue.latLng.latitude
//            val longitude = venue.latLng.longitude
//            val latLng = LatLng(latitude, longitude)
//            it.apply {
//                addMarker(MarkerOptions().position(latLng).title(venue.name))
//                animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
//            }
//        }
//        childFragmentManager.beginTransaction().replace(R.id.mapsView, mapFragment).commit()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        toolbar(R.drawable.ic_arrow_back_black_24dp, args.Conference.name)
//        initView()
//    }
//
//    private fun initView() {
//        view.showConference(args.Conference)
//    }
//}