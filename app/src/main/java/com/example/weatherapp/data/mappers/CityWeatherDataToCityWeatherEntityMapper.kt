package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.mappers.mappersForDb.CityDataToCityEntity
import com.example.weatherapp.data.mappers.mappersForDb.WeatherDataToWeatherEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import java.util.*

class CityWeatherDataToCityWeatherEntityMapper {

    private val weatherDataToWeatherEntity =
        WeatherDataToWeatherEntity().weatherDataToWeatherEntity

    private val cityDataToCityEntity = CityDataToCityEntity().cityDataToCityEntity

    val cityWeatherDataToCityWeatherEntityMapper: (data: CityWeatherData) -> CityWeatherEntity =
        { it.toEntity() }

    private fun CityWeatherData.toEntity() =
        CityWeatherEntity(
            city = cityDataToCityEntity(city),
            weather = weatherDataToWeatherEntity(weather),
            isLike = true
        )

}