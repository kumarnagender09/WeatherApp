package com.mphasis.weather.utils

object TempUtil {
    fun Double.kelvinToCelsius(): Int {
        return (this - 273.15).toInt() // Convert Kelvin to Celsius
    }

    fun Double.kelvinToFahrenheit(): Int {
        return ((this - 273.15) * 9 / 5 + 32).toInt() // Convert Kelvin to Fahrenheit
    }
}
