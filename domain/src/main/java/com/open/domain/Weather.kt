package com.open.domain


data class Weather(
    var weather: ArrayList<WeatherResponse> = arrayListOf(),
    var main: Main? = Main(),

    )


data class WeatherResponse(
    var id: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
)


data class Main(
    var temp: Double? = null,
    var feels_like: Double? = null,
    var temp_min: Double? = null,
    var temp_max: Double? = null,
    var pressure: Int? = null,
    var humidity: Int? = null,
    var sea_level: Int? = null,
    var grnd_level: Int? = null

)

