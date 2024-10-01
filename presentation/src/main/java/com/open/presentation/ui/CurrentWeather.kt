package com.open.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.open.data.network.WeatherState
import com.open.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    // Collect the weather state from the ViewModel
    val weatherState by viewModel.weatherState.collectAsState()

    // State for the city input
    var city by remember { mutableStateOf(TextFieldValue("")) }

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
            is WeatherState.Loading -> CircularProgressIndicator()
            is WeatherState.Success -> {
                val weatherData = (weatherState as WeatherState.Success).data
                Text("Weather in ${weatherData.name}: ${weatherData.main?.temp}")
                // You can add more details from weatherData here
            }
            is WeatherState.Error -> {
                val errorMessage = (weatherState as WeatherState.Error).message
                Text("Error: $errorMessage")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    // Provide a preview of the WeatherScreen
//    WeatherScreen(viewModel = WeatherViewModel())
}
