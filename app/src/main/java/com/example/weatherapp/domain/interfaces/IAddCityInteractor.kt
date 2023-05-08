package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.flow.Flow

interface IAddCityInteractor {

    suspend fun getAndSortSitiesFromRemoteAndLocalRep(): TRezult<List<CityEntity>>

    suspend fun addLikedCityToLocalRep(cityEntity: CityEntity)

    suspend fun deleteCityFromLocalRep(cityEntity: CityEntity)
    val cityFavoriteList: Flow<List<CityWeatherEntity>>

    suspend fun updateWeather()
}