package com.example.weatherapp.data.mappers.mappersForDb

import com.example.weatherapp.data.localRep.WeatherData
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.utils.logError

class WeatherEntityToWeatherData {

    val weatherEntityToWeatherData: (weather: WeatherEntity?) -> WeatherData? = { it?.toEntity() }

    private fun WeatherEntity.toEntity() = runCatching {
        WeatherData(
            temp = temp,
            season = season,
            feelsLike = feelsLike,
            condition = condition,
            country = country,
            city = city,
            province = province,
            date = date.time,
            timeZone = timeZone,
            imageUrl = imageUrl
        )
    }.getOrElse {
        it.logError()
        null
    }
}