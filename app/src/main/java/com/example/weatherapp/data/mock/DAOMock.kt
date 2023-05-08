package com.example.weatherapp.data.mock

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.dataBase.Dao

object DAOMock:Dao{

    override fun getAllDataFromDatabase(): List<CityWeatherData> {
        return CityWeatherDataMock().list
    }

    override fun addOrUpdateCityToDatabase(cityWeatherData: CityWeatherData) {
        CityWeatherDataMock().list.add(cityWeatherData)
    }

    override fun deleteDataByCityNameFromDatabase(cityName: String) {
        for (i in CityWeatherDataMock().list){
            if (i.city.cityName == cityName){
                CityWeatherDataMock().list.remove(i)
            }
        }
    }

    override fun getDataByCityNameFromDatabase(cityName: String): CityWeatherData? {
        var cityWeatherDataMock:CityWeatherData? = null
        for (i in CityWeatherDataMock().list) {
            if (i.city.cityName == cityName) {
                cityWeatherDataMock = i
            }
        }
        return cityWeatherDataMock
    }
}