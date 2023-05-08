package com.example.weatherapp.utils

sealed class TRezult<T>(
    open val data: T? = null,
    open val exception: WeatherException? = null
) {
    data class Success<T>(override val data: T) : TRezult<T>()
    data class Error<T>(override val exception: WeatherException) :
        TRezult<T>()
}


