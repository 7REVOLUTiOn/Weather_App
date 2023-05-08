package com.example.weatherapp.domain.implementations

import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCityInteractorImpl(
    private val getCitiesFromRemoteRepositoryUseCase: suspend () -> TRezult<List<CityEntity>>,
    private val getDataFromLocalRepositoryUseCase: suspend () -> List<CityWeatherEntity>,
    private val addCityToFavoriteUseCase: suspend (CityEntity) -> Unit,
    private val deleteCityFromFavotiteUseCase: suspend (CityEntity) -> Unit
) : IAddCityInteractor {


    private val _cityFavoriteList = MutableStateFlow<List<CityWeatherEntity>>(emptyList())
    override val cityFavoriteList: Flow<List<CityWeatherEntity>> = _cityFavoriteList

    init {
        MainScope().launch(Dispatchers.IO) {
            updateFavotiteList()
        }
    }

    private suspend fun updateFavotiteList() = withContext(Dispatchers.IO) {
        val data = getDataFromLocalRepositoryUseCase.invoke()
        _cityFavoriteList.emit(data)
    }


    override suspend fun addLikedCityToLocalRep(cityEntity: CityEntity) =
        withContext(Dispatchers.IO) {
            addCityToFavoriteUseCase.invoke(cityEntity)
            updateFavotiteList()
        }

    override suspend fun deleteCityFromLocalRep(cityEntity: CityEntity) =
        withContext(Dispatchers.IO) {
            deleteCityFromFavotiteUseCase.invoke(cityEntity)
            updateFavotiteList()
        }

    override suspend fun updateWeather() {
        withContext(Dispatchers.IO) {
            updateFavotiteList()
        }
    }

    override suspend fun getAndSortSitiesFromRemoteAndLocalRep(): TRezult<List<CityEntity>> =
        withContext(Dispatchers.IO) {
            val citiesFromRemoteRep = getCitiesFromRemoteRepositoryUseCase.invoke()
            val cityWeatherEntityFromLocalRep = getDataFromLocalRepositoryUseCase.invoke()

            return@withContext when (citiesFromRemoteRep) {
                is TRezult.Error -> citiesFromRemoteRep
                is TRezult.Success -> {
                    citiesFromRemoteRep.data.forEach { cityNtework ->
                        val isLike = cityWeatherEntityFromLocalRep.find { cityBD ->
                            cityNtework.cityName == cityBD.city.cityName
                        } != null
                        cityNtework.isLiked = isLike
                    }

                    TRezult.Success(citiesFromRemoteRep.data)
                }
            }
        }
}