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

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState
    val uiState: WeatherUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { viewModel.getWeather(it) },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
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