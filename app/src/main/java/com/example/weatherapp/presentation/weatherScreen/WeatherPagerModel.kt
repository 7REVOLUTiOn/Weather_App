package com.example.weatherapp.presentation.weatherScreen

import com.example.weatherapp.domain.entities.CityWeatherEntity

sealed class WeatherPagerModel(
    open val city: CityWeatherEntity? = null
) {


    data class City(
        override val city: CityWeatherEntity
    ) : WeatherPagerModel()

    class AddNewCity() : WeatherPagerModel()

    class CurrentCity : WeatherPagerModel()


}
