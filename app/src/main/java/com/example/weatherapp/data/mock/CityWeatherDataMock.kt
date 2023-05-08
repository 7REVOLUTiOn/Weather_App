package com.example.weatherapp.data.mock

import com.example.weatherapp.data.dataBase.CityWeatherData
import com.example.weatherapp.data.localRep.CityData

class CityWeatherDataMock {

     val list = mutableListOf<CityWeatherData>(
         CityWeatherData(0, CityData("Minsk",53.5359,27.3400,true), null),
         CityWeatherData(1, CityData("Moscow",55.75396,37.620393,true), null),
         CityWeatherData(2, CityData("London",51.5085,0.12574,true), null))
}