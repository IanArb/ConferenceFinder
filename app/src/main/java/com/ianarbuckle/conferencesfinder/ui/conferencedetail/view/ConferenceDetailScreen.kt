package com.ianarbuckle.conferencesfinder.ui.conferencedetail.view

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.ColorRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.NavController
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.viewmodel.ConferenceDetailViewModel
import com.ianarbuckle.conferencesfinder.ui.conferences.model.UIViewState
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ErrorContent
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme
import conferences.model.*
import conferences.model.LatLng as ConferenceLatLng

@Composable
fun ConferenceDetailScreen(id: String, viewModel: ConferenceDetailViewModel, navController: NavController) {
    viewModel.init(id)

    val observeState = viewModel.observeUiState.observeAsState()
    val uiState = observeState.value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        androidx.compose.material.Icon(
                            asset = Icons.Filled.ArrowBack,
                            tint = Color.White
                        )
                    }
                },
                title = {
                    if (uiState is UIViewState.Success<*>) {
                        val conference = uiState.result as Conference
                        Text(text = conference.name)
                    }
                })
        },
        bodyContent = {
            when (uiState) {
                is UIViewState.Success<*> -> {
                    Row {
                        DetailScreen(uiState.result as Conference)
                    }
                }
                is UIViewState.Error -> ErrorContent()
            }
        }
    )

}

@Composable
private fun DetailScreen(conference: Conference) {
    Column {
        VenueMapView(conference.location.venue)
        Spacer(Modifier.preferredHeight(12.dp))
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = conference.location.venue.name,
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.preferredHeight(8.dp))
            Row {
                Icon(
                    asset = Icons.Default.LocationOn
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = conference.location.venue.address,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(Modifier.preferredHeight(8.dp))
            Row {
                Icon(asset = Icons.Default.Info)
                Text(
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp),
                    text = conference.callForPapers.websiteUrl,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Blue
                )
            }
        }

    }
}

@Composable
private fun VenueMapView(venue: Venue) {
    val mapView = rememberMapViewWithLifecycle()
    MapViewContainer(map = mapView, venue)
}

@Composable
private fun MapViewContainer(
    map: MapView,
    venue: Venue,
) {
    AndroidView({ map }, modifier = Modifier.preferredHeight(400.dp)) { mapView ->
        mapView.getMapAsync { map ->

            val latLng = venue.latLng

            map.uiSettings.isZoomControlsEnabled = true

            val position = LatLng(latLng.latitude, latLng.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f))

            val icon = bitmapDescriptorFromVector(
                mapView.context,
                R.drawable.ic_location_on_24px,
                R.color.colorRed
            )
            val markerOptions = MarkerOptions()
                .title(venue.name)
                .position(position)
                .icon(icon)

            map.addMarker(markerOptions)
        }
    }
}

private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int, @ColorRes tintColor: Int? = null): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)

    // add the tint if it exists
    tintColor?.let {
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, it))
    }
    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

@Preview
@Composable
fun ConferenceDetailPreview() {
    val london = Conference(
        id = "1",
        name = "London Droidcon",
        logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Tower_Bridge_from_Shad_Thames.jpg/1200px-Tower_Bridge_from_Shad_Thames.jpg",
        location = Location(
            country = Country(
                "United Kingdom",
                "London"),
            venue = Venue(
                name = "Business Design Centre",
                address = "52 Upper St, The Angel, London",
                latLng = ConferenceLatLng(51.536209,  -0.105351)
            )
        ),
        dates = Dates("30th", "31st November"),
        callForPapers = CallForPapers(
            startDate = "",
            endDate = "",
            websiteUrl = "https://www.london.droidcon.com/"
        ),
        status = "UPCOMING"
    )

    ConferencesTheme {
        DetailScreen(london)
    }
}
