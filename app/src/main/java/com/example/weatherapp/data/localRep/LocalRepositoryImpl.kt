package com.example.weatherapp.data.localRep

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.mappers.CityEntityAndWeatherEntityToCityWeatherDataMapper
import com.example.weatherapp.data.mappers.CityWeatherDataToCityWeatherEntityMapper
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.interfaces.ILocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val getAllDataFromDatabaseUseCase: suspend () -> List<CityWeatherData>,
    private val addCityToLocalRepositoryUseCase: suspend (CityWeatherData) -> Unit,
    private val deleteDataByCityNameFromDatabaseUseCase: suspend (String) -> Unit,
    private val updateCityWeatherDataUseCase: suspend (CityWeatherData) -> Unit,
    private val getDataByCityNameFromDatabaseUseCase: suspend (String) -> CityWeatherData?
) : ILocalRepository {

    val cityEntityToCityWeatherDataMapper =
        CityEntityAndWeatherEntityToCityWeatherDataMapper().weatherEntityToData

    val cityWeatherDataToCityWeatherEntityMapper =
        CityWeatherDataToCityWeatherEntityMapper().cityWeatherDataToCityWeatherEntityMapper

    override suspend fun addCityToLocalRepository(cityEntity: CityEntity) {
        val cityWeatherData = cityEntityToCityWeatherDataMapper(cityEntity, null)
        addCityToLocalRepositoryUseCase.invoke(cityWeatherData)
    }

    override suspend fun deleteCityFromLocalRepository(cityEntity: CityEntity) {
        deleteDataByCityNameFromDatabaseUseCase.invoke(cityEntity.cityName)
    }

    override suspend fun getAllCityWeatherEntityFromDb(): List<CityWeatherEntity> =
        withContext(Dispatchers.IO) {
            val listOfCityWeatherData = getAllDataFromDatabaseUseCase.invoke()
            val listOfCityWeatherEntity = listOfCityWeatherData.map {
                cityWeatherDataToCityWeatherEntityMapper(it)
            }
            return@withContext listOfCityWeatherEntity
        }

    override suspend fun updateCity(cityWeatherEntity: CityWeatherEntity) {
        val oldCityWeatherData = getDataByCityNameFromDatabaseUseCase.invoke(cityWeatherEntity.city.cityName)
        if (oldCityWeatherData != null) {
            val cityWeatherData =
                cityEntityToCityWeatherDataMapper(cityWeatherEntity.city, cityWeatherEntity.weather)
            val newCityWeatherData = oldCityWeatherData.copy(weather = cityWeatherData.weather)
            updateCityWeatherDataUseCase.invoke(newCityWeatherData)
        }

    }
}