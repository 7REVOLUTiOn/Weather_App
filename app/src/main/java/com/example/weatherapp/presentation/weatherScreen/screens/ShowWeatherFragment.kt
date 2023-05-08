package com.example.weatherapp.presentation.weatherScreen.screens

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentShowWeatherBinding
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.utils.GetTimeFormatByTimeZone
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ShowWeatherFragment : Fragment(R.layout.fragment_show_weather) {

    private val binding by viewBinding(FragmentShowWeatherBinding::bind)
    private val viewModel by viewModel<ShowWeatherFragmentViewModel> {
        val cityWeatherEntityString = arguments?.getString(CITY_WEATHER_ENTITY)
        val cityWeatherEntity =
            Gson().fromJson(cityWeatherEntityString, CityWeatherEntity::class.java)
        parametersOf(cityWeatherEntity)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cityInfo.observe(liveDataOwner) {
            binding.weatherView.delelteClickListener = {
                viewModel.deleteCityFromFavotite(it.city)
            }
            binding.weatherView.temp = getString(R.string.temp, it.weather?.temp)
            binding.weatherView.cityName = it.city.cityName
            binding.weatherView.feelsLike = getString(R.string.feels_lik, it.weather?.temp)
            binding.weatherView.condtions = it.weather?.condition
            binding.weatherView.iconUrl = it.weather?.imageUrl
            binding.weatherView.date =
                GetTimeFormatByTimeZone.getTimeFormatByTimeZone(it.weather?.timeZone)

        }




        viewModel.backgorund.observe(liveDataOwner) {
            binding.weatherView.background1 = it
        }

        viewModel.hide.observe(liveDataOwner) {
            binding.weatherView.isVisible = it
        }

        viewModel.isLoading.observe(liveDataOwner) {
            binding.weatherView.isLoading = it
        }

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.getWeather()
                isRefreshing = false
            }
        }

    }

    companion object {

        private const val CITY_WEATHER_ENTITY = "CITY_WEATHER_ENTITY"
        fun getInstance(cityWeatherEntity: CityWeatherEntity): ShowWeatherFragment {
            val fragment = ShowWeatherFragment()
            val bundle = Bundle()
            bundle.putString(CITY_WEATHER_ENTITY, Gson().toJson(cityWeatherEntity))
            fragment.arguments = bundle
            return fragment
        }
    }

}
