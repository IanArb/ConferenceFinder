package com.ianarbuckle.conferencesfinder.ui.conferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferenceScreen
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ErrorContent
import com.ianarbuckle.conferencesfinder.ui.conferences.view.LoadingContent
import com.ianarbuckle.conferencesfinder.ui.conferences.model.UIViewState
import com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel.ConferencesViewModel
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme
import conferences.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConferenceActivity : AppCompatActivity() {

    private val viewModel: ConferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConferencesTheme {
                HomeScreen(viewModel)
            }
        }

    }

    @Composable
    fun HomeScreen(viewModel: ConferencesViewModel) {
        val observeState = viewModel.conferencesObservable.observeAsState()
        val uiState = observeState.value

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Conferences")
                })
            },
            bodyContent = {
                when (uiState) {
                    is UIViewState.Success<*> -> {
                        ConferenceScreen(
                            uiState.result as List<Conference>,
                            it,
                            this
                        )
                    }
                    is UIViewState.Error -> ErrorContent()
                    is UIViewState.Loading -> LoadingContent()
                }
            }
        )
    }

    @Preview
    @Composable
    fun HomePreview() {
        ConferencesTheme {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = "Conferences")
                    })
                },
                bodyContent = {
                    ConferenceScreen(
                        conferences(),
                        it,
                        this
                    )
                }
            )
        }
    }

    fun conferences(): List<Conference> {
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
        return listOf(london, berlin)
    }

}
