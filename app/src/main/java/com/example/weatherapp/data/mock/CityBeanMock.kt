package com.example.weatherapp.data.mock

import com.example.weatherapp.data.beans.CityBean
import com.example.weatherapp.utils.AnyJson.fromJson

class CityBeanMock {
    fun getSomeInvalid() = """
        [
            {
              "cityName":"Minsk"
             

            },
            
            {
              
              "lat": "55.75396",
              "lon": "37.620393"
            },
            
            {
              "cityName":"London",
              "lat": "51.5085",
              "lon": "0.12574"
            },
            
            {
              "cityName":"New-York",
              "lat": "40.7143",
              "lon": "74.006"
            },
            
            {
              "cityName":"Amsterdam",
              "lat": "52.374",
              "lon": "4.88969"
            },
            
             {
              "cityName":"Berlin",
            
              "lon": "13.4105"
            },
            
             {
              "cityName":"Pekin",
              "lat": "39.9075",
              "lon": "116.397"
            },
            
             {
              "cityName":"Tokyo",
              "lat": "35.6895",
              "lon": "139.692"
            },
            
            {
              "cityName":"Petropavlovsk-Kamchatsky",
              "lat": "53.0444",
              "lon": "158.651"
            },
            
             {
              "cityName":"Krasnoyarsk",
              "lat": "56.0184",
              "lon": "92.8672"
            }
           
        ]
    """.trimIndent().fromJson<List<CityBean>>()

        fun getValidBeans() ="""
            [
                {
                  "cityName":"Minsk",
                  "lat": "53.5359",
                  "lon": "27.3400"
                },
                
                {
                  "cityName": "Moscow",
                  "lat": "55.75396",
                  "lon": "37.620393"
                },
                
                {
                  "cityName":"London",
                  "lat": "51.5085",
                  "lon": "0.12574"
                },
                
                {
                  "cityName":"New-York",
                  "lat": "40.7143",
                  "lon": "74.006"
                },
                
                {
                  "cityName":"Amsterdam",
                  "lat": "52.374",
                  "lon": "4.88969"
                },
                
                 {
                  "cityName":"Berlin",
                  "lat": "52.5244",
                  "lon": "13.4105"
                },
                
                 {
                  "cityName":"Pekin",
                  "lat": "39.9075",
                  "lon": "116.397"
                },
                
                 {
                  "cityName":"Tokyo",
                  "lat": "35,6895",
                  "lon": "139.692"
                },
                
                {
                  "cityName":"Petropavlovsk-Kamchatsky",
                  "lat": "53.0444",
                  "lon": "158.651"
                },
                
                 {
                  "cityName":"Krasnoyarsk",
                  "lat": "56.0184",
                  "lon": "92.8672"
                }
               
            ]
        """.trimIndent().fromJson<List<CityBean>>()


}