package com.example.weatherapp.domain.implementations

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.domain.interfaces.IShowWeatherInteractor
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowWeatherInteractorImpl(
    private val getWeatherFromRemoteRepositoryUseCase: suspend (CityEntity) -> TRezult<WeatherEntity>,
    private val updateDatabaseUseCase: suspend (CityWeatherEntity) -> Unit,
) : IShowWeatherInteractor {


    override suspend fun getWeather(cityWeatherEntity: CityWeatherEntity) =
        withContext(Dispatchers.IO) {
            val weather = getWeatherFromRemoteRepositoryUseCase.invoke(cityWeatherEntity.city)
            return@withContext when (weather) {
                is TRezult.Error -> {
                    TRezult.Error(weather.exception)
                }
                is TRezult.Success -> {
                    updateDatabaseUseCase(cityWeatherEntity.copy(weather = weather.data))
                    TRezult.Success(cityWeatherEntity.copy(weather = weather.data))
                }
            }
        }
}