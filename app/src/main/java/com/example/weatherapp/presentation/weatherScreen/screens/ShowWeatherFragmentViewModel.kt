package com.example.weatherapp.presentation.weatherScreen.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entities.CityEntity
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.utils.GetBackgroundByTime
import com.example.weatherapp.utils.GetTimeFormatByTimeZone
import com.example.weatherapp.utils.LiveDataUtils.asLiveData
import com.example.weatherapp.utils.LiveDataUtils.mValue
import com.example.weatherapp.utils.TRezult
import com.example.weatherapp.utils.logInfo
import kotlinx.coroutines.launch

class ShowWeatherFragmentViewModel(
    val cityWeatherEntity: CityWeatherEntity,
    val showWeatherUseCase: suspend (CityWeatherEntity) -> TRezult<CityWeatherEntity>,
    val deleteFragment: suspend (CityEntity) -> Unit,
) : ViewModel() {

    private val _cityInfo = MutableLiveData<CityWeatherEntity>()
    val cityInfo = _cityInfo.asLiveData()

    private val _backgorund = MutableLiveData<Int>()
    val backgorund = _backgorund.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _hideAll = MutableLiveData<Boolean>()
    val hide = _hideAll.asLiveData()



    init {
        //todo разбить на функции - чтобы все не было в одном init!!!!
        _cityInfo.mValue = cityWeatherEntity

        if (cityWeatherEntity.weather == null) {
            _isLoading.mValue = true
            _hideAll.mValue = false
        } else {
            _backgorund.mValue = GetBackgroundByTime.returnBackgroundIntByTime(cityWeatherEntity.weather.date,cityWeatherEntity.weather.timeZone)
        }
        getWeather()
    }

    fun deleteCityFromFavotite(cityEntity: CityEntity) {
        viewModelScope.launch {
            deleteFragment(cityEntity)
        }
    }



    fun getWeather(){

        viewModelScope.launch {
            _isLoading.mValue = true
            val weatherFromUseCase = showWeatherUseCase.invoke(cityWeatherEntity)
            when(weatherFromUseCase){
                is TRezult.Success -> {
                    if (weatherFromUseCase.data.weather?.timeZone != null){
                        kotlin.runCatching {
                            _backgorund.mValue  = weatherFromUseCase.data.weather.backGround
                        }
                    }
                    _cityInfo.mValue = weatherFromUseCase.data
                    _hideAll.mValue = true
                    _isLoading.mValue = false
                }
                is TRezult.Error ->{
                    //todo сделать какой нибудь single live event или toast - чтобы выкинуть ошибку
                }
            }

        }

    }
}
