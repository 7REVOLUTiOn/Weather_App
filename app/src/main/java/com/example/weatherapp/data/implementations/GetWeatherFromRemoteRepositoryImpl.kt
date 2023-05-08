package com.example.weatherapp.data.implementations

import com.example.weatherapp.data.beans.WeatherBean
import com.example.weatherapp.data.mappers.WeatherBeanToWeatherEntityMapper
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.WeatherException
import com.example.weatherapp.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWeatherFromRemoteRepositoryImpl(
    private val getWeatherByCoordinatesFromRemoteRepositoryUseCase: suspend (lat: Double, lon: Double) -> WeatherBean
) : IGetWeatherFromRemoteRepository {


    override suspend fun getWeatherFromRemoteRepository(city: CityEntity): TRezult<WeatherEntity> =
        withContext(Dispatchers.IO) {
            return@withContext runCatching {
                val weatherBeanToWeatherEntityMapper =
                    WeatherBeanToWeatherEntityMapper().weatherBeanToEntity
                val weatherBean = getWeatherByCoordinatesFromRemoteRepositoryUseCase.invoke(city.lat, city.lon)
                val weatherEntity = weatherBeanToWeatherEntityMapper(weatherBean)
                if (weatherEntity != null) {
                    TRezult.Success(weatherEntity)
                } else {
                    throw Exception("Mapping error")
                }
            }.getOrElse {
                it.logError()
                TRezult.Error(WeatherException.get(it))
            }
        }
}