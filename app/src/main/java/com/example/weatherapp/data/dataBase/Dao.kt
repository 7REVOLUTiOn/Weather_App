package com.example.weatherapp.data.dataBase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Query("SELECT * FROM CityWeatherData")
    fun getAllDataFromDatabase(): List<CityWeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrUpdateCityToDatabase(cityWeatherData: CityWeatherData)

    @Query("DELETE FROM CityWeatherData WHERE cityName = :cityName")
    fun deleteDataByCityNameFromDatabase(cityName: String)

    @Query("SELECT * FROM CityWeatherData WHERE cityName = :cityName")
    fun getDataByCityNameFromDatabase(cityName: String): CityWeatherData?

}