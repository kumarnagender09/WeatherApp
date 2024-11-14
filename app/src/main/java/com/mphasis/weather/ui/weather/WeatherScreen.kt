package com.mphasis.weather.ui.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mphasis.weather.ui.weather.components.DefaultAppBar
import com.mphasis.weather.ui.weather.components.SearchAppBar
import com.mphasis.weather.ui.weather.components.WeatherScreenContent
import com.mphasis.weather.ui.weather.components.WeatherTopAppBar

// A Composable function representing the main weather screen UI.
// It displays the weather details and includes a top bar with a search feature.
@Composable
fun WeatherScreen(
    // Modifier is used to apply additional styling or layout behavior to the screen's root composable.
    modifier: Modifier = Modifier,

    // ViewModel is injected using Hilt and provides data and logic for the WeatherScreen.
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    // State representing the current search widget state (opened or closed).
    val searchWidgetState by viewModel.searchWidgetState

    // State holding the current text in the search input field.
    val searchTextState by viewModel.searchTextState

    // UI state from the ViewModel which holds weather data, loading state, and any error message.
    val uiState: WeatherUiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Scaffold is used to define the layout structure, providing the AppBar and main content.
    Scaffold(
        topBar = {
            // The top app bar contains the search functionality.
            WeatherTopAppBar(
                // Passes the current state of the search widget and search text to the top bar.
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                // Handles text changes in the search input.
                onTextChange = { viewModel.updateSearchTextState(it) },
                // Closes the search widget when the close button is clicked.
                onCloseClicked = { viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                // Calls the ViewModel's method to fetch weather data based on the search input when the search button is clicked.
                onSearchClicked = { viewModel.getWeather(it) },
                // Opens the search widget when the search icon is clicked.
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { paddingValues ->
            // The main content area is a Surface composable that handles the screen's background color and layout.
            Surface(
                modifier = Modifier
                    .fillMaxSize()  // Makes the surface fill the entire screen.
                    .padding(paddingValues), // Applies padding based on Scaffold's provided padding values.
                color = MaterialTheme.colorScheme.background  // Sets the background color according to the theme.
            ) {
                // WeatherScreenContent displays the actual weather data based on the current UI state.
                WeatherScreenContent(uiState = uiState, modifier = modifier, viewModel = viewModel)
            }
        },
    )
}


@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultAppBar(onSearchClicked = {})
}
//
@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Search for a city",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}