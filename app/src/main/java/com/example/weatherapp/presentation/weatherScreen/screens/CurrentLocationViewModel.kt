package com.example.weatherapp.presentation.weatherScreen.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.utils.GetBackgroundByTime
import com.example.weatherapp.utils.LiveDataUtils.asLiveData
import com.example.weatherapp.utils.LiveDataUtils.mValue
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrentLocationViewModel(
    private val getWeatherByLocation: suspend () -> TRezult<WeatherEntity>,
) : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName = _cityName.asLiveData()

    private val _weatherInfo = MutableLiveData<WeatherEntity>()
    val weatherInfo = _weatherInfo.asLiveData()


    private val _location = MutableLiveData<String>()
    val location = _location.asLiveData()

    private val _backgorund = MutableLiveData<Int>()
    val backgorund = _backgorund.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _hideAll = MutableLiveData<Boolean>()
    val hide = _hideAll.asLiveData()


    fun getWeather() {

        _hideAll.mValue = false

        viewModelScope.launch {
            val weather = getWeatherByLocation.invoke()
            when (weather) {
                is TRezult.Error -> { //todo("допилить")
                }
                is TRezult.Success -> {

                    if (weather.data.city != null) {
                        _location.mValue = weather.data.city
                    } else {
                        if (weather.data.province != null) {
                            _location.mValue = weather.data.province
                        } else {
                            _location.mValue = weather.data.country
                        }
                    }
                    viewModelScope.launch {
                        _isLoading.mValue = true
                        _backgorund.mValue = GetBackgroundByTime.returnBackgroundIntByTime(
                            weather.data.date,
                            weather.data.timeZone
                        )
                        _weatherInfo.mValue = weather.data
                        delay(1000)
                        _isLoading.mValue = false
                    }

                }
            }
            _hideAll.mValue = true
        }
    }


}