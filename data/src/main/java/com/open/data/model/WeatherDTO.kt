package com.open.data.model

data class WeatherDTO(
    val name: String,           // The city name (directly fetched from the API)
    val main: Main,             // A nested object containing temperature information
    val weather: List<WeatherDescription>  // A list of weather descriptions
)

data class Main(
    val temp: Float  // Temperature in Kelvin (or another scale)
)

data class WeatherDescription(
    val description: String  // Weather description, e.g., "Clear sky"
)