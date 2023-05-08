package com.example.weatherapp.presentation.weatherScreen.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCurrentLocationBinding
import com.example.weatherapp.utils.GetTimeFormatByTimeZone
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import org.koin.androidx.viewmodel.ext.android.viewModel


class CurrentLocationFragment : Fragment(R.layout.fragment_current_location) {


    private val binding by viewBinding(FragmentCurrentLocationBinding::bind)
    private val viewModel by viewModel<CurrentLocationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLocationPermission()

        binding.weatherView.binding.deleteButton.isVisible = false


        viewModel.weatherInfo.observe(liveDataOwner){
            binding.weatherView.temp = getString(R.string.temp, it.temp)
            binding.weatherView.feelsLike = getString(R.string.feels_lik, it.temp)
            binding.weatherView.condtions = it.condition
            binding.weatherView.iconUrl = it.imageUrl
            binding.weatherView.date =
                GetTimeFormatByTimeZone.getTimeFormatByTimeZone(it.timeZone)
        }


        viewModel.location.observe(liveDataOwner) {
            binding.weatherView.cityName = it
        }


        viewModel.hide.observe(liveDataOwner) {
            binding.weatherView.hide = it
        }

        viewModel.backgorund.observe(liveDataOwner) {
            binding.weatherView.background1 = it
        }

        viewModel.isLoading.observe(liveDataOwner) {
            binding.weatherView.isLoading = it
        }




    }

    private val loauncherLocationPermission =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                viewModel.getWeather()
            } else {
                checkLocationPermission()
            }
        }

    private fun checkLocationPermission() {
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            loauncherLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            viewModel.getWeather()
            binding.weatherView.hide = true
        }
    }

}