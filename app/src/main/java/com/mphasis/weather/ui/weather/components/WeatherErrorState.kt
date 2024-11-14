package com.mphasis.weather.ui.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mphasis.weather.R
import com.mphasis.weather.ui.weather.WeatherUiState
import com.mphasis.weather.ui.weather.WeatherViewModel

@Composable
public fun WeatherErrorState(
    modifier: Modifier = Modifier,
    uiState: WeatherUiState,
    viewModel: WeatherViewModel?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Animation(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            animation = R.raw.animation_loading,
        )

        Button(onClick = { viewModel?.getWeather() }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Retry",
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.retry),
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            modifier = modifier
                .weight(2f)
                .alpha(0.5f)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            text = "Something went wrong: ${uiState.errorMessage}",
            textAlign = TextAlign.Center,
        )
    }
}
