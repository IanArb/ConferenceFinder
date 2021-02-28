package com.ianarbuckle.conferencesfinder.ui.conferences

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.viewmodel.ConferenceDetailViewModel
import com.ianarbuckle.conferencesfinder.ui.conferencedetail.view.ConferenceDetailScreen
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferenceScreen
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ErrorContent
import com.ianarbuckle.conferencesfinder.ui.conferences.view.LoadingContent
import com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel.ConferencesViewModel
import com.ianarbuckle.conferencesfinder.ui.theme.ConferencesTheme
import conferences.model.*
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope

@AndroidEntryPoint
class ConferenceActivity : ComponentActivity() {

    private val viewModel: ConferencesViewModel by viewModels()
    private val detailsViewModel: ConferenceDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ConferencesTheme {
                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(viewModel, navController)
                    }
                    composable(
                        "detail/{conferenceId}",
                        arguments = listOf(navArgument("conferenceId") { type = NavType.StringType })) {
                        ConferenceDetailScreen(it.arguments?.getString("conferenceId") ?: "", detailsViewModel, navController)
                    }
                }
            }
        }

    }

    @Composable
    fun HomeScreen(viewModel: ConferencesViewModel, navController: NavController) {
        lifecycleScope.launchWhenStarted {
            viewModel.init()
        }

        val observeState = viewModel.uiState.collectAsState()
        val uiState = observeState.value

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Conferences")
                })
            },
            content = {
                when {
                    uiState.isLoading -> {
                        LoadingContent()
                    }
                    uiState.isError -> {
                        ErrorContent()
                    }
                    !uiState.data.isNullOrEmpty() -> {
                        ConferenceScreen(
                            conferences = uiState.data,
                            innerPadding = it,
                            navController = navController)
                    }
                }
            }
        )
    }

}
