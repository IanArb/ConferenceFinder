package com.ianarbuckle.conferencesfinder.ui.conferencedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.view.ConferenceDetailLayout
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme
import conferences.model.*

fun launchConferenceDetailsActivity(context: Context, conference: Conference) {
    context.startActivity(createConferenceDetailsIntent(context, conference))
}

private fun createConferenceDetailsIntent(context: Context, conference: Conference): Intent {
    val intent = Intent(context, ConferenceDetailsActivity::class.java)
    intent.putExtra("conference", conference)
    return intent
}

class ConferenceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val conference = intent.getParcelableExtra<Conference>("conference")

        setContent {
            ConferencesTheme {
                ConferenceDetailsScreen(conference)
            }
        }

    }

    @Composable
    fun ConferenceDetailsScreen(conference: Conference?) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            finish()
                        }) {
                            Icon(
                                asset =Icons.Filled.ArrowBack,
                                tint = Color.White
                            )
                        }
                    },
                    title = {
                        Text(text = conference?.name ?: "")
                    })
            },
            bodyContent = {
                conference?.let { conference -> ConferenceDetailLayout(conference) }
            }
        )
    }

    @Preview
    @Composable
    fun DetailsPreview() {
        ConferencesTheme {
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
                        latLng = LatLng(51.536209,  -0.105351)
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
            ConferenceDetailsScreen(london)
        }
    }
}