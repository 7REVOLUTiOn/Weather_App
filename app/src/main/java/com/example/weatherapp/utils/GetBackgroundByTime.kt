package com.example.weatherapp.utils


import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*
object GetBackgroundByTime {

    private val random = Random()

    private val listOfNigtsImagies = listOf(
        R.drawable.night1,
        R.drawable.night2,
        R.drawable.night3,
        R.drawable.night4,
        R.drawable.night5,
        R.drawable.night5,
        R.drawable.night6,
        R.drawable.night7,
        R.drawable.night8,
        R.drawable.night9,
        R.drawable.night10,
        R.drawable.night11,
        R.drawable.night12,
        R.drawable.night13,
        R.drawable.night14,
        R.drawable.night15
    )


    private val listOfDayImageis = listOf(
        R.drawable.day1,
        R.drawable.day2,
        R.drawable.day3,
        R.drawable.day4,
        R.drawable.day5,
        R.drawable.day6
    )

    private val listOfEarlyMorningImageis = listOf(
        R.drawable.early_morning1
    )

    fun returnBackgroundIntByTime(date: Date, timeZone: String): Int =
        kotlin.runCatching {
            val hours = SimpleDateFormat("HH")
            hours.timeZone = TimeZone.getTimeZone(timeZone)


             when (hours.format(date.time).toInt()) {
                 in 0..5 -> getRandomImageForBackgorund(listOfNigtsImagies)
                 in 6..8 -> getRandomImageForBackgorund(listOfEarlyMorningImageis)
                 in 9..12 -> getRandomImageForBackgorund(listOfDayImageis)
                 in 13..21 -> getRandomImageForBackgorund(listOfDayImageis)
                 in 22..24 -> getRandomImageForBackgorund(listOfNigtsImagies)
                 else -> R.drawable.gradient_color_exception
             }
        }.getOrElse {
            R.drawable.gradient_color_exception
        }

    private fun getRandomImageForBackgorund(list:List<Int>): Int {
         //todo - мб перенести в обьект обьекта а не осталвять в методе
        return list[random.nextInt(list.size)]
    }
}