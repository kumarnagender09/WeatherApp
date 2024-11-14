package com.mphasis.weather.data.model

import com.google.gson.annotations.SerializedName

// Main data class to hold the weather response
data class WeatherResponse(
    @SerializedName("coord") val coord: Coord, // Coordinates (lon, lat)
    @SerializedName("weather") val weather: List<WeatherInfo>, // List of weather conditions
    @SerializedName("base") val base: String, // Base station for weather data
    @SerializedName("main") val main: Main, // Main weather data like temperature
    @SerializedName("visibility") val visibility: Int, // Visibility in meters
    @SerializedName("wind") val wind: Wind, // Wind data (speed, direction)
    @SerializedName("clouds") val clouds: Clouds, // Cloud coverage percentage
    @SerializedName("dt") val dt: Long, // Timestamp of the weather data
    @SerializedName("sys") val sys: Sys, // System data like country and sunrise/sunset times
    @SerializedName("timezone") val timezone: Int, // Timezone offset from UTC
    @SerializedName("id") val id: Int, // City ID
    @SerializedName("name") val name: String, // City name
    @SerializedName("cod") val cod: Int // HTTP status code for the request
) {
    // Data class for Coordinates (longitude and latitude)
    data class Coord(
        @SerializedName("lon") val lon: Double, // Longitude
        @SerializedName("lat") val lat: Double // Latitude
    )

    // Data class for weather information (e.g., main, description)
    data class WeatherInfo(
        @SerializedName("id") val id: Int, // Weather condition ID
        @SerializedName("main") val main: String, // Short description of the weather
        @SerializedName("description") val description: String, // Detailed weather description
        @SerializedName("icon") val icon: String // Icon representing the weather condition
    )

    // Data class for main weather data (temperature, humidity, pressure, etc.)
    data class Main(
        @SerializedName("temp") val temp: Double, // Current temperature in Kelvin
        @SerializedName("feels_like") val feelsLike: Double, // Feels like temperature in Kelvin
        @SerializedName("temp_min") val tempMin: Double, // Minimum temperature in Kelvin
        @SerializedName("temp_max") val tempMax: Double, // Maximum temperature in Kelvin
        @SerializedName("pressure") val pressure: Int, // Atmospheric pressure in hPa
        @SerializedName("humidity") val humidity: Int, // Humidity percentage
        @SerializedName("sea_level") val seaLevel: Int? = null, // Sea level pressure (optional)
        @SerializedName("grnd_level") val grndLevel: Int? = null // Ground level pressure (optional)
    )

    // Data class for wind data (speed and direction)
    data class Wind(
        @SerializedName("speed") val speed: Double, // Wind speed in meters per second
        @SerializedName("deg") val deg: Int // Wind direction in degrees
    )

    // Data class for cloud coverage percentage
    data class Clouds(
        @SerializedName("all") val all: Int // Cloud coverage percentage
    )

    // Data class for system-related information (e.g., country, sunrise/sunset times)
    data class Sys(
        @SerializedName("type") val type: Int, // Type of data (usually 1)
        @SerializedName("id") val id: Int, // ID of the system
        @SerializedName("country") val country: String, // Country code
        @SerializedName("sunrise") val sunrise: Long, // Sunrise timestamp
        @SerializedName("sunset") val sunset: Long // Sunset timestamp
    )
}
