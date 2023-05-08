package com.example.weatherapp.data.mappers.mappersForDb

import com.example.weatherapp.data.localRep.CityData
import com.example.weatherapp.domain.entities.CityEntity

class CityDataToCityEntity {

    val cityDataToCityEntity: (city: CityData) -> CityEntity = { it.toEntity() }

    private fun CityData.toEntity() =
        CityEntity(
            cityName = cityName,
            lat = lat,
            lon = lon,
            isLiked = isLiked
        )

}