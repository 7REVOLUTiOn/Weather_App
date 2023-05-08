package com.example.weatherapp.data.gpsRep

import com.example.weatherapp.domain.entities.CityEntity
import kotlinx.coroutines.flow.Flow

interface IDataCurrentLocationrRep {

    val location: Flow<CityEntity>
    fun startListener()

    fun closeListener()
}