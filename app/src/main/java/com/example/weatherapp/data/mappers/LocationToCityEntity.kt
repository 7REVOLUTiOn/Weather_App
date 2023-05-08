package com.example.weatherapp.data.mappers

import android.location.Location
import com.example.weatherapp.domain.entities.CityEntity

class LocationToCityEntity {

    val locationToCityEntity: (location:Location) -> CityEntity =
        { it.toEntity() }

    private fun Location.toEntity() =
        CityEntity(
           cityName = "MyLocation",
            lat = latitude,
            lon = longitude,
            isLiked = true
        )
}