package com.open.common

object Contants {
}





object AppStrings {

    // HomeScreen -> ForecastSection
    const val hourly_forecast = "Hourly Forecast"
    const val daily_forecast = "Daily Forecast"

    // HomeScreen -> WeatherDetailSection
    const val temp = "🌡 TEMP"
    const val feels_like = "🌡 FEELS LIKE"
    const val cloudiness = "☁ CLOUDINESS"
    const val humidity = "💧 HUMIDITY"
    const val sunrise = "🌇 SUNRISE"
    const val sunset = "🌆 SUNSET"
    const val wind = "🌬 WIND"
    const val metric = "KM"
    const val pressure = "⏲ PRESSURE"
    const val degree = "°"

    // SearchCityScreen
    const val topbar_title = "Weather"

    // SearchCityScreen -> SearchField
    const val placeholder = "Search for a city"

    // SearchCityScreen -> CityWeatherList
    const val subtitle1 = "My Cities"
    const val subtitle2 = "Search Result"
    const val no_city = "You don't have any city"

    // SearchCityScreen -> SearchCityScreenContent
    const val error_title = "OOOOPS!!!"
}

object WeatherConditions {
    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

object MainWeatherConditions {
    const val CLOUDS = "Clouds"
    const val SNOW = "Snow"
    const val RAIN = "Rain"
    const val THUNDERSTORM = "Thunderstorm"
    const val CLEAR = "Clear"
}


object ExceptionTitles {
    const val GPS_DISABLED = "GPS Disabled"
    const val NO_PERMISSION = "No Permission"
    const val NO_INTERNET_CONNECTION = "No Internet Connection"
    const val UNKNOWN_ERROR = "Unknown Error"
}

object ExceptionDescriptions {
    const val GPS_DISABLED_DESCR = "Your GPS seems to be disabled, please enable it."
    const val NO_PERMISSION_DESCR = "Allow otherwise location tracking won't work."
    const val NO_INTERNET_CONNECTION_DESCR = "Please check your internet connection."
    const val UNKNOWN_ERROR_DESCR = "Something went wrong."
}

object ErrorCardConsts {
    const val BUTTON_TEXT = "OK"
}
