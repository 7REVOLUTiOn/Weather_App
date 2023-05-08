package com.example.weatherapp.data.retrofit

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.beans.WeatherBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast")
    @Headers("X-Yandex-API-Key:9c63106a-ecf3-4586-9a82-d2df6e914e31")
    suspend fun getWeatherFromYandex(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): WeatherBean

    companion object {
        private var weatherAPI: WeatherAPI? = null

        fun getInstance(): WeatherAPI {
            if (weatherAPI == null) {
                val okHttpClient = MyOkHttp(
                    isSafe = !BuildConfig.DEBUG
                ).get()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.weather.yandex.ru/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                weatherAPI = retrofit.create(WeatherAPI::class.java)
            }
            return weatherAPI!!
        }
    }
}