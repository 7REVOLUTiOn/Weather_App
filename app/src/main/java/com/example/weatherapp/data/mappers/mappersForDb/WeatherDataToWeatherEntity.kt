package com.example.weatherapp.data.mappers.mappersForDb

import com.example.weatherapp.R
import com.example.weatherapp.data.localRep.WeatherData
import com.example.weatherapp.domain.entities.WeatherEntity
import java.util.*

class WeatherDataToWeatherEntity {


    val weatherDataToWeatherEntity: (weather: WeatherData?) -> WeatherEntity? = { it?.toEntity() }

    private fun WeatherData.toEntity() =
        WeatherEntity(
            temp = temp,
            season = season,
            feelsLike = feelsLike,
            condition = condition,
            country = country,
            city = city,
            province = province,
            date = Date(date),
            backGround = when (Date().hours) {
                in 0..5 -> R.color.night
                in 6..8 -> R.color.early_morning
                in 9..12 -> R.color.morning
                in 13..21 -> R.color.day
                in 22..24 -> R.color.night
                else -> R.color.black
            },
            timeZone = timeZone,
            imageUrl = imageUrl
        )
}