package com.example.weatherapp.data.localRep

data class WeatherData(
    val temp: String,
    val season: String,
    val feelsLike: String,
    val condition: String,
    val country: String,
    val city: String?,
    val province: String?,
    val date: Long,
    val timeZone: String,
    val imageUrl: String
)
