package com.example.weatherapp.data.mappers.mappersForDb

import com.example.weatherapp.data.localRep.CityData
import com.example.weatherapp.domain.entities.CityEntity

class CityEntityToCityData {


    val cityEntityToCityData: (city: CityEntity) -> CityData = { it.toEntity() }

    private fun CityEntity.toEntity() =
        CityData(
            cityName = cityName,
            lat = lat,
            lon = lon,
            isLiked = isLiked
        )


}