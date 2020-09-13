package com.ianarbuckle.conferencesfinder.ui.conferences.view

import android.content.Context
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import coil.request.ImageRequest
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.launchConferenceDetailsActivity
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme
import conferences.model.*
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

@Composable
fun ConferenceScreen(conferences: List<Conference>, innerPadding: InnerPadding, context: Context) {
    LazyColumnFor(
        contentPadding = innerPadding,
        modifier = Modifier.padding(16.dp),
        items = conferences
    ) { conference ->
        ConferenceCard(conference) {
            launchConferenceDetailsActivity(context, it)
        }
    }
}

@Composable
private fun ConferenceCard(conference: Conference, itemClick: (item: Conference) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                itemClick(conference)
            }),
        shape = RoundedCornerShape(10.dp),
        elevation = 6.dp
    ) {
        Row {
            BodyContent(conference)
        }

    }
}

@Composable
private fun BodyContent(conference: Conference) {
    Column {
        Box {
            CoilImageWithCrossfade(
                modifier = Modifier.preferredHeight(150.dp),
                request = ImageRequest.Builder(ContextAmbient.current)
                    .data(conference.logoUrl)
                    .build(),
                contentScale = ContentScale.FillWidth
            )
        }
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = conference.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Icon(
                        asset = Icons.Default.LocationOn
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "${conference.location.country.city}, ${conference.location.country.name}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Icon(
                        asset = Icons.Default.DateRange
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "${conference.dates.startDate} - ${conference.dates.endDate}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = conference.status,
                    color = StatusColor(conference.status)
                )
            }
        }

    }
}

@Composable
private fun StatusColor(status: String): Color {
    return when(status) {
        "UPCOMING" -> Color.Green
        "CANCELLED" -> Color.Red
        else -> Color.Black
    }
}

@Preview(showBackground = true)
@Composable
fun ConferencesPreview() {
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
                address = "",
                latLng = LatLng(0.0, 0.0)
            )
        ),
        dates = Dates("30th", "31st November"),
        callForPapers = CallForPapers(
            startDate = "",
            endDate = "",
            websiteUrl = ""
        ),
        status = "UPCOMING"
    )

    val berlin = Conference(
        id = "1",
        name = "Berlin Droidcon",
        logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Tower_Bridge_from_Shad_Thames.jpg/1200px-Tower_Bridge_from_Shad_Thames.jpg",
        location = Location(
            country = Country(
                "Germany",
                "Berlin"),
            venue = Venue(
                name = "Berlin Design Centre",
                address = "",
                latLng = LatLng(0.0, 0.0)
            )
        ),
        dates = Dates("20th", "22nd August"),
        callForPapers = CallForPapers(
            startDate = "29th",
            endDate = "31st",
            websiteUrl = ""
        ),
        status = "ONLINE"
    )
    val conferences = listOf(london, berlin)
    ConferencesTheme {
        ConferenceScreen(conferences, InnerPadding(16.dp), ContextAmbient.current)
    }
}