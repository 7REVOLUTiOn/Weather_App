package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.utils.TRezult

interface IShowWeatherInteractor {

    suspend fun getWeather(cityWeatherEntity: CityWeatherEntity): TRezult<CityWeatherEntity>

}