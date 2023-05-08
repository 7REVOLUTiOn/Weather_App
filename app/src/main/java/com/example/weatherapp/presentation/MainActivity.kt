package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.domain.interfaces.ILocalRepository
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.domain.interfaces.IGetWeatherFromRemoteRepository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}