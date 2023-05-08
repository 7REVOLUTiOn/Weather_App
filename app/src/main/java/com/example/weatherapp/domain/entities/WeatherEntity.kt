package com.example.weatherapp.domain.entities

import java.util.*

data class WeatherEntity(
    val temp: String,
    val season: String,
    val feelsLike: String,
    val condition: String,
    val country: String,
    val city: String?,
    val province: String?,
    val date: Date,
    var backGround: Int,
    val timeZone: String,
    val imageUrl: String,
)


