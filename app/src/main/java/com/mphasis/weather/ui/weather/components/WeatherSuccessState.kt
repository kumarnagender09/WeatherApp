package com.mphasis.weather.ui.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mphasis.weather.R
import com.mphasis.weather.ui.weather.WeatherUiState
import com.mphasis.weather.utils.DateUtil.toFormattedDate
import com.mphasis.weather.utils.TempUtil.kelvinToCelsius

@Composable
public fun WeatherSuccessState(
    modifier: Modifier,
    uiState: WeatherUiState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = uiState.weatherResponse?.name.orEmpty(),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = uiState.weatherResponse?.dt?.toFormattedDate().orEmpty(),
            style = MaterialTheme.typography.bodyLarge
        )

        AsyncImage(
            modifier = Modifier.size(64.dp),
            model = stringResource(
                R.string.icon_image_url,
                uiState.weatherResponse?.weather?.get(0)?.icon.orEmpty(),
            ),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            error = painterResource(id = R.drawable.ic_placeholder),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
        )
        Text(
            text = stringResource(
                R.string.temperature_value_in_celsius,
                uiState.weatherResponse?.main?.temp?.kelvinToCelsius().toString()
            ),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = uiState.weatherResponse?.weather?.get(0)?.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = stringResource(
                R.string.feels_like_temperature_in_celsius,
                uiState.weatherResponse?.main?.feelsLike?.kelvinToCelsius().toString()
            ),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        ) {
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.wind_speed_label),
                weatherValue = uiState.weatherResponse?.wind?.speed.toString(),
                weatherUnit = stringResource(R.string.wind_speed_unit),
                iconId = R.drawable.ic_wind,
            )
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.pressure_index_label),
                weatherValue = uiState.weatherResponse?.main?.pressure.toString(),
                weatherUnit = stringResource(R.string.uv_unit),
                iconId = R.drawable.ic_uv,
            )
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.humidity_label),
                weatherValue = uiState.weatherResponse?.main?.humidity.toString(),
                weatherUnit = stringResource(R.string.humidity_unit),
                iconId = R.drawable.ic_humidity,
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}