package com.example.weatherapp.data.gpsRep

import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.example.weatherapp.data.mappers.LocationToCityEntity
import com.example.weatherapp.domain.entities.CityEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class DataCurrentLocationrRepImpl(private val application: Application) : LocationListener,
    IDataCurrentLocationrRep {

    private val _location = MutableStateFlow<CityEntity?>(null)
    override val location = _location.mapNotNull { it }

    private val locationManager =
        application.getSystemService(Context.LOCATION_SERVICE) as LocationManager


    override fun onLocationChanged(p0: Location) {
        _location.value = LocationToCityEntity().locationToCityEntity(p0)
    }

    override fun startListener() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this)
    }

    override fun closeListener() {
        _location.value = null
        locationManager.removeUpdates(this)
    }
}