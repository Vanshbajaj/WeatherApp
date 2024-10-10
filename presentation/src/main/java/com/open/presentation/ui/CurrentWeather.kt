package com.open.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.open.presentation.ui.components.ForecastLazyRow
import com.open.common.AppStrings
import com.open.data.model.WeatherResponse
import com.open.data.network.WeatherState
import com.open.presentation.ui.components.CircularProgressBar
import com.open.presentation.ui.components.CurrentWeatherDetailRow
import com.open.presentation.ui.components.ForecastTitle
import com.open.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    // Collect the weather state from the ViewModel
    val weatherState by viewModel.weatherState.collectAsState()

    // State for the city input
    var city by remember { mutableStateOf(TextFieldValue("")) }
    BackgroundImage()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField for city input
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City") },
            modifier = Modifier.fillMaxWidth()
        )


        // Button to fetch weather
        Button(onClick = {
            if (city.text.isNotBlank()) {
                viewModel.getWeather(city.text)
            }
        }) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the appropriate content based on the weather state
        when (weatherState) {
            is WeatherState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
                }
            }
            is WeatherState.Success -> {

                val weatherData = (weatherState as WeatherState.Success).data
               // Text("Weather in ${weatherData.name}: ${weatherData.main?.temp}")
                // You can add more details from weatherData here
                CurrentWeatherSection(weatherData)
                DetailsSection(weatherData)
            }

            is WeatherState.Error -> {
                val errorMessage = (weatherState as WeatherState.Error).message
                Text("Error: $errorMessage")
            }
        }
    }
}

@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = com.open.presentation.R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}




@Composable
private fun WeatherDetailSection(currentWeather: WeatherResponse) {
    CurrentWeatherDetailRow(
        title1 = AppStrings.temp,
        value1 = "${currentWeather.main?.temp}${AppStrings.degree}",
        title2 = AppStrings.feels_like,
        value2 = "${currentWeather.main?.feels_like}${AppStrings.degree}"
    )
    CurrentWeatherDetailRow(
        title1 = AppStrings.cloudiness,
        value1 = "${currentWeather.clouds}%",
        title2 = AppStrings.humidity,
        value2 = "${currentWeather.main?.humidity}%"
    )
//    CurrentWeatherDetailRow(
//        title1 = AppStrings.sunrise,
//        value1 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunrise)}AM",
//        title2 = AppStrings.sunset,
//        value2 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunset)}PM"
//    )
//    CurrentWeatherDetailRow(
//        title1 = AppStrings.wind,
//        value1 = "${currentWeather.main[0].wind.speed}${AppStrings.metric}",
//        title2 = AppStrings.pressure,
//        value2 = "${currentWeather.weatherList[0].weatherData.pressure}"
//    )
}

@Composable
private fun ForecastSection(forecastData: WeatherResponse) {
    ForecastTitle(text = AppStrings.hourly_forecast)
    forecastData.weather?.take(8)?.let { ForecastLazyRow(forecasts = it) }
    ForecastTitle(text = AppStrings.daily_forecast)
    forecastData.weather?.let { ForecastLazyRow(forecasts = it.takeLast(32)) }
}


@Composable
private fun DetailsSection(forecast: WeatherResponse) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ForecastSection(forecast)
                WeatherDetailSection(forecast)
            }
        }
    }
}




@Composable
private fun CurrentWeatherSection(todayWeather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayWeather.name.toString(),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "${todayWeather.main?.temp?.toInt()}${"C"}",
            style = MaterialTheme.typography.headlineSmall
        )
        todayWeather.weather?.get(0)?.let {
            Text(
                text = it.description,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )
        }

    }
}

//////////////////
@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    // Provide a preview of the WeatherScreen
    //  BackgroundImage()
    // WeatherScreen(viewModel = WeatherViewModel(hiltViewModel<>()))
}
