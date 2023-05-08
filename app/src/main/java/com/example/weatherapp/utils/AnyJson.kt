package com.example.weatherapp.utils

import com.example.weatherapp.utils.AnyJson.toJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AnyJson {
    val gson = Gson()
    inline fun <reified T> T.toJson(): String = gson.toJson(this, object : TypeToken<T>() {}.type)
    inline fun <reified T> String.fromJson(): T = gson.fromJson(this, object : TypeToken<T>() {}.type)
    inline fun <reified T> T.copyAny(): T = this.toJson().fromJson()
}