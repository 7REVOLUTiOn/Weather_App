package com.example.weatherapp.data.implementations

import com.example.weatherapp.data.beans.CityBean
import com.example.weatherapp.data.mappers.CityBeanToCityEntityMapper
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.WeatherException
import com.example.weatherapp.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCitiesFromRemoteRepositoryImpl(
    private val getCitiesFromRemoteRepUseCase: suspend () -> List<CityBean>
) : IGetCitiesFromRemoteRepository {


    override suspend fun getCitiesFromRemoteRepository(): TRezult<List<CityEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext runCatching {
                val listOfCityBean = getCitiesFromRemoteRepUseCase.invoke()
                val cityBeanToCityEntityMapper = CityBeanToCityEntityMapper().citiesBeanItem
                val listOfCityEntity = listOfCityBean.mapNotNull {
                    cityBeanToCityEntityMapper(it)
                }
                TRezult.Success(listOfCityEntity)
            }.getOrElse {

                it.logError()
                TRezult.Error(WeatherException.get(it))
            }
        }
}