package com.example.weatherapp.data.localRep

data class CityData(
    val cityName: String,
    val lat: Double,
    val lon: Double,
    var isLiked: Boolean
)
