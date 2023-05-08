package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

object GetTimeFormatByTimeZone {

    fun getTimeFormatByTimeZone(timeZone:String?): String {
       return kotlin.runCatching {
            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone))
            val date = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
           logInfo("${timeZone}")
            dateFormat.format(date.time)
        }.getOrElse {
             "Date"
        }

    }
}