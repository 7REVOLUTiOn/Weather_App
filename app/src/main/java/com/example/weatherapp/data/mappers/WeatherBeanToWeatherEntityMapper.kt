package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.beans.WeatherBean
import com.example.weatherapp.domain.entities.WeatherEntity
import com.example.weatherapp.utils.GetBackgroundByTime
import com.example.weatherapp.utils.logError
import com.example.weatherapp.utils.logInfo
import java.util.*


class WeatherBeanToWeatherEntityMapper {

    val weatherBeanToEntity: (bean: WeatherBean) -> WeatherEntity? = { it.toEntity() }


    private fun WeatherBean.toEntity() = runCatching {
        WeatherEntity(
            temp = fact.temp,
            season = fact.season,
            feelsLike = fact.feelsLike,
            condition = fact.condition,
            country = geoObject.country.name,
            city = geoObject.locality?.name,
            province = geoObject.province?.name,
            date = Date(),
            backGround = GetBackgroundByTime.returnBackgroundIntByTime(
                date = Date(),
                timeZone = info.tzinfo.name.toString()
            ),
            timeZone = info.tzinfo.name.toString(),
            imageUrl = "https://yastatic.net/weather/i/icons/funky/dark/${fact.icon}.svg."
        )
    }.getOrElse {
        logInfo("Маппер А ВОТ ОШШИБКА ----> ${it.logError()}")
        it.logError()
        null
    }

}