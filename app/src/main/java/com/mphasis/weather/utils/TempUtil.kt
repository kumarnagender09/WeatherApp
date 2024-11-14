package com.mphasis.weather.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object TempUtil {
    fun Double.kelvinToCelsius(): Int {
        return (this - 273.15).toInt()
    }

    fun Double.kelvinToFahrenheit(): Int {
        return ((this - 273.15) * 9 / 5 + 32).toInt()
    }
}
