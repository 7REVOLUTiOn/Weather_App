package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity


interface ILocalRepository {

    suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity>

    suspend fun addCityToLocalRepository(cityEntity: CityEntity)

    suspend fun deleteCityFromLocalRepository(cityEntity: CityEntity)

    suspend fun updateCity(cityWeatherEntity: CityWeatherEntity)


}