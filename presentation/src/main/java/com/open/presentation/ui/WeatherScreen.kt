package com.open.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.open.data.network.UiState
import com.open.presentation.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    var city by remember { mutableStateOf("") }
    val weatherState by viewModel.weatherState.collectAsState()

    // Debugging Column setup
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Debug: Ensure BasicTextField is rendered
        TextField(
            value = city,
            onValueChange = { city = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Trigger the weather fetch when the "Done" action is pressed
                    viewModel.getWeather(city)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textStyle = MaterialTheme.typography.titleSmall.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Handle different UI states to display appropriate content
        when (weatherState) {
            is UiState.Loading -> {
                // Show loading spinner while the API call is being processed
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                val weather = (weatherState as UiState.Success).data
                // Display the weather data when the API call is successful
                Text(text = "Weather: ${weather.main?.temp}")
            }

            is UiState.Error -> {
                val errorMessage = (weatherState as UiState.Error).message
                // Display error message if the API call fails
                Text(text = "Error: $errorMessage")
            }

            is UiState.Empty -> {
                // Show a placeholder or an empty state when no API call has been made
                Text(text = "Enter a city to get weather information")
            }
        }
    }
}
