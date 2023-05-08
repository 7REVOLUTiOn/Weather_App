package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.utils.TRezult

interface IGetWeatherByLocationInteractor {

    suspend fun getWeatherByLocation(): TRezult<WeatherEntity>

    suspend fun getWeatherFromCacheOrForce():TRezult<WeatherEntity>


}