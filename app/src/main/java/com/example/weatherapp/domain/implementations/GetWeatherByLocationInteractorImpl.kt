package com.example.weatherapp.domain.implementations

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.domain.interfaces.IGetWeatherByLocationInteractor
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetWeatherByLocationInteractorImpl(
    private val startLocalListener: suspend () -> Unit,
    private val currentLocationAsCityEntity: Flow<CityEntity>,
    private val getWeatherFromRemoteRepository: suspend (CityEntity) -> TRezult<WeatherEntity>,
    private val stopListenerLocation: suspend () -> Unit,
) : IGetWeatherByLocationInteractor {

    private var cacheWeather: TRezult<WeatherEntity>? = null


    override suspend fun getWeatherByLocation(): TRezult<WeatherEntity> {
        startLocalListener.invoke()
        val cityEntity = currentLocationAsCityEntity.first()
        val weatherEntity = getWeatherFromRemoteRepository.invoke(cityEntity)
        cacheWeather = weatherEntity
        stopListenerLocation.invoke()
        return weatherEntity
    }


    override suspend fun getWeatherFromCacheOrForce(): TRezult<WeatherEntity> {
        if (cacheWeather?.data == null) {
            getWeatherByLocation()
        }
        return cacheWeather!!
    }
}


