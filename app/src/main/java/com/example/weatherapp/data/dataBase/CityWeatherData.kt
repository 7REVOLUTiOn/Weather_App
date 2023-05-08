package com.example.weatherapp.data.dataBase

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.localRep.CityData
import com.example.weatherapp.data.localRep.WeatherData

@Entity(tableName = "CityWeatherData")
data class CityWeatherData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @Embedded
    val city: CityData,

    @Embedded
    val weather: WeatherData?,
)
