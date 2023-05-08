package com.example.weatherapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.getBooleanOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ViewCityWeatherBinding
import com.example.weatherapp.utils.logInfo

class CityWeatherView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {
    val binding by lazy { ViewCityWeatherBinding.inflate(LayoutInflater.from(context), this, true) }
    private val xml = context.theme.obtainStyledAttributes(
        attrs,
        R.styleable.CityWeatherView,
        0,
        R.style.CityWeatherView
    )


    var cityName: String =
        xml.getStringOrThrow(R.styleable.CityWeatherView__cityName_CityWeatherView).apply {
            cityName = this
        }
        set(value) {
            field = value
            binding.cityName.text = field
        }

    var temp: String? =
        xml.getStringOrThrow(R.styleable.CityWeatherView__temp_CityWeatherView).apply {
            temp = this
        }
        set(value) {
            field = value
            binding.temp.text = field
        }

    var date: String =
        xml.getStringOrThrow(R.styleable.CityWeatherView__date_CityWeatherView).apply {
            date = this
        }
        set(value) {
            field = value
            binding.lastUpdate.text = field
        }


    var feelsLike: String =
        xml.getStringOrThrow(R.styleable.CityWeatherView__feelsLike_CityWeatherView).apply {
            feelsLike = this
        }
        set(value) {
            field = value
            binding.feelsLiked.text = field
        }

    var condtions: String? =
        xml.getStringOrThrow(R.styleable.CityWeatherView__conditions_CityWeatherView).apply {
            condtions = this
        }
        set(value) {
            field = value
            binding.description.text = field?.uppercase()
        }


    var background1: Int? =
        xml.getResourceIdOrThrow(R.styleable.CityWeatherView__backrground_CityWeatherView).apply {
            background1 = this
        }
        //разобраться с xml.getIntOrThrow
        set(value) {
            field = value
            binding.constraint.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    background1!!
                )
            )
        }

    var iconUrl: String? = null //разобраться с xml.getStringOrThrow
        set(value) {
            field = value
            logInfo("${field}")
            Glide.with(this).load(iconUrl?.dropLast(1)).into(binding.iconOfWeather)
        }

    var isLoading: Boolean =
        xml.getBooleanOrThrow(R.styleable.CityWeatherView__isLoading_CityWeatherView)
        set(value) {
            field = value
            binding.progressBar.isVisible = field
        }

    var hide: Boolean =
        xml.getBooleanOrThrow(R.styleable.CityWeatherView__hideAll_CityWeatherView)
        set(value) {
            field = value
            binding.temp.isVisible = field
            binding.feelsLiked.isVisible = field
            binding.deleteButton.isVisible = field
            binding.cityName.isVisible = field
            binding.lastUpdate.isVisible = field
            binding.description.isVisible = field
        }


    var delelteClickListener: (() -> Unit)? = null
        set(value) {
            field = value
            binding.deleteButton.setOnClickListener {
                field?.invoke()
            }
        }

    init {
        binding
        xml.recycle()
    }

}