package com.example.weatherapp.presentation.weatherScreen

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.presentation.weatherScreen.screens.AddCityScreenFragment
import com.example.weatherapp.presentation.weatherScreen.screens.CurrentLocationFragment
import com.example.weatherapp.presentation.weatherScreen.screens.ShowWeatherFragment

class ViewPagerAdapter(fm: FragmentManager, lifeCycle:Lifecycle) : FragmentStateAdapter(fm,lifeCycle) {

    val listOfWeatherPagerModel = mutableListOf<WeatherPagerModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(weatherPagerList: List<WeatherPagerModel>) {
        listOfWeatherPagerModel.clear()
        listOfWeatherPagerModel.addAll(weatherPagerList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listOfWeatherPagerModel.size
    }

    override fun createFragment(position: Int): Fragment {


        val item = listOfWeatherPagerModel[position]
        return when (item) {
            is WeatherPagerModel.AddNewCity -> {
                val weatherScreenFragment = AddCityScreenFragment()
                return weatherScreenFragment
            }
            is WeatherPagerModel.City -> {
                ShowWeatherFragment.getInstance(item.city)
            }

            is WeatherPagerModel.CurrentCity -> {
                CurrentLocationFragment() }
        }
    }
}

