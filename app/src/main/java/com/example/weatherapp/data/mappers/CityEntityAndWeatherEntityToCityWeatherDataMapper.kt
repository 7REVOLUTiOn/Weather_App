package com.example.weatherapp.data.mappers


import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.mappers.mappersForDb.CityEntityToCityData
import com.example.weatherapp.data.mappers.mappersForDb.WeatherEntityToWeatherData
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity

class CityEntityAndWeatherEntityToCityWeatherDataMapper {


    val cityEntityToCityData =
        CityEntityToCityData().cityEntityToCityData

    val weatherEntityToWeatherData = WeatherEntityToWeatherData().weatherEntityToWeatherData

    val weatherEntityToData: (cityEntity: CityEntity, weatherEntity: WeatherEntity?) -> CityWeatherData =
        { cityEntity, weatherEntity ->
            CityWeatherData(
                id = 0,
                cityEntityToCityData(cityEntity),
                weatherEntityToWeatherData(weatherEntity)
            )
        }

}