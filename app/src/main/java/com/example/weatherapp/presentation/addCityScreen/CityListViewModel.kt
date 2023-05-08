package com.example.weatherapp.presentation.addCityScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.presentation.weatherScreen.WeatherPagerModel
import com.example.weatherapp.utils.LiveDataUtils.asLiveData
import com.example.weatherapp.utils.LiveDataUtils.mValue
import com.example.weatherapp.utils.LiveDataUtils.setIfNewValue
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.WeatherException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityListViewModel(
    private val getCitiesListUseCase: suspend () -> TRezult<List<CityEntity>>,
    private val addCityToFavoriteUseCase: suspend (CityEntity) -> Unit,
    private val deleteCityFromFavoriteUseCase: suspend (CityEntity) -> Unit,
    private val favoriteListFlow: Flow<List<CityWeatherEntity>>
) : ViewModel() {

    private var job: Job? = null

    private val _favoriteList = MutableLiveData<List<WeatherPagerModel>>()
    val favoriteList = _favoriteList.asLiveData()

    private val _listOfCityEntity = MutableLiveData<List<CityEntity>>()
    val listOfCityEntity: LiveData<List<CityEntity>> = _listOfCityEntity.asLiveData()

    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _error = MutableLiveData<WeatherException>()
    val error = _error.asLiveData()

    private val dataCitiesList = mutableListOf<CityEntity>()

    private fun collectFavoriteFlow() {
        viewModelScope.launch {
            favoriteListFlow.collect {
                val list: MutableList<WeatherPagerModel> = it.map {
                    WeatherPagerModel.City(it)
                }.toMutableList()
                list.add(WeatherPagerModel.AddNewCity())
                list.add(0, WeatherPagerModel.CurrentCity())
                _favoriteList.mValue = list
            }
        }
    }

    fun addCityToFavorite(cityEntity: CityEntity) {
        viewModelScope.launch {
            cityEntity.isLiked = true
            addCityToFavoriteUseCase.invoke(cityEntity)
            updateCitiesList(dataCitiesList, _searchText.value ?: "")
        }
    }


    fun deleteCityFromFavorite(cityEntity: CityEntity) {
        viewModelScope.launch {
            cityEntity.isLiked = false
            deleteCityFromFavoriteUseCase.invoke(cityEntity)
            updateCitiesList(dataCitiesList, _searchText.value ?: "")
        }
    }

    fun setSearchText(value: String) {
        _searchText.setIfNewValue(value)
        if (value.isEmpty()) {
            _listOfCityEntity.mValue = dataCitiesList
        } else {
            _listOfCityEntity.mValue = dataCitiesList.filter {
                it.cityName.contains(value, true)
            }
        }
    }


    init {
        loadingAllCitiesAsync()
        collectFavoriteFlow()
    }

    private fun loadingAllCitiesAsync() {
        job?.cancel()
        job = viewModelScope.launch {
            loadingAllCities()
        }
    }


    suspend fun loadingAllCities() = withContext(Dispatchers.Main) {
        _isLoading.mValue = true
        val reuzlt = getCitiesListUseCase.invoke()

        when (reuzlt) {
            is TRezult.Error -> {
                _error.mValue = reuzlt.exception

            }
            is TRezult.Success -> {
                updateCitiesList(reuzlt.data, "")
            }
        }
        _isLoading.mValue = false
    }

    private fun sortingByLikedOrNotLiked(list: List<CityEntity>): List<CityEntity> {
        return list.sortedWith(compareByDescending(CityEntity::isLiked).thenBy(CityEntity::cityName))
    }

    private fun updateCitiesList(newList: List<CityEntity>, searchText: String) {
        val sortList = sortingByLikedOrNotLiked(newList)
        dataCitiesList.clear()
        dataCitiesList.addAll(sortList)
        setSearchText(searchText)
    }
}