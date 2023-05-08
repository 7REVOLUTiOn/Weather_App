package com.example.weatherapp.data.beans

import com.google.gson.annotations.SerializedName

data class WeatherBean(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("forecasts")
    val forecasts: List<Forecast?>?,
    @SerializedName("geo_object")
    val geoObject: GeoObject,
    @SerializedName("info")
    val info: Info,
    @SerializedName("now")
    val now: Int?,
    @SerializedName("now_dt")
    val nowDt: String?,
    @SerializedName("yesterday")
    val yesterday: Yesterday?
) {
    data class Fact(
        @SerializedName("cloudness")
        val cloudness: Double?,
        @SerializedName("condition")
        val condition: String,
        @SerializedName("daytime")
        val daytime: String?,
        @SerializedName("feels_like")
        val feelsLike: String,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("is_thunder")
        val isThunder: Boolean?,
        @SerializedName("obs_time")
        val obsTime: Int?,
        @SerializedName("polar")
        val polar: Boolean?,
        @SerializedName("prec_prob")
        val precProb: Int?,
        @SerializedName("prec_strength")
        val precStrength: Double?,
        @SerializedName("prec_type")
        val precType: Int?,
        @SerializedName("pressure_mm")
        val pressureMm: Int?,
        @SerializedName("pressure_pa")
        val pressurePa: Int?,
        @SerializedName("season")
        val season: String,
        @SerializedName("soil_moisture")
        val soilMoisture: Double?,
        @SerializedName("soil_temp")
        val soilTemp: Int?,
        @SerializedName("source")
        val source: String?,
        @SerializedName("temp")
        val temp: String,
        @SerializedName("uptime")
        val uptime: Int?,
        @SerializedName("uv_index")
        val uvIndex: Int?,
        @SerializedName("wind_dir")
        val windDir: String?,
        @SerializedName("wind_gust")
        val windGust: Double?,
        @SerializedName("wind_speed")
        val windSpeed: Double?
    )

    data class Forecast(
        @SerializedName("biomet")
        val biomet: Biomet?,
        @SerializedName("date")
        val date: String?,
        @SerializedName("date_ts")
        val dateTs: Int?,
        @SerializedName("hours")
        val hours: List<Hour?>?,
        @SerializedName("moon_code")
        val moonCode: Int?,
        @SerializedName("moon_text")
        val moonText: String?,
        @SerializedName("parts")
        val parts: Parts?,
        @SerializedName("rise_begin")
        val riseBegin: String?,
        @SerializedName("set_end")
        val setEnd: String?,
        @SerializedName("sunrise")
        val sunrise: String?,
        @SerializedName("sunset")
        val sunset: String?,
        @SerializedName("week")
        val week: Int?
    ) {
        data class Biomet(
            @SerializedName("condition")
            val condition: String?,
            @SerializedName("index")
            val index: Int?
        )

        data class Hour(
            @SerializedName("cloudness")
            val cloudness: Double?,
            @SerializedName("condition")
            val condition: String?,
            @SerializedName("feels_like")
            val feelsLike: Int?,
            @SerializedName("hour")
            val hour: String?,
            @SerializedName("hour_ts")
            val hourTs: Int?,
            @SerializedName("humidity")
            val humidity: Int?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("is_thunder")
            val isThunder: Boolean?,
            @SerializedName("prec_mm")
            val precMm: Double?,
            @SerializedName("prec_period")
            val precPeriod: Int?,
            @SerializedName("prec_prob")
            val precProb: Int?,
            @SerializedName("prec_strength")
            val precStrength: Double?,
            @SerializedName("prec_type")
            val precType: Int?,
            @SerializedName("pressure_mm")
            val pressureMm: Int?,
            @SerializedName("pressure_pa")
            val pressurePa: Int?,
            @SerializedName("soil_moisture")
            val soilMoisture: Double?,
            @SerializedName("soil_temp")
            val soilTemp: Int?,
            @SerializedName("temp")
            val temp: Int?,
            @SerializedName("uv_index")
            val uvIndex: Int?,
            @SerializedName("wind_dir")
            val windDir: String?,
            @SerializedName("wind_gust")
            val windGust: Double?,
            @SerializedName("wind_speed")
            val windSpeed: Double?
        )

        data class Parts(
            @SerializedName("day")
            val day: Day?,
            //@SerializedName("day_short")
            //val dayShort: Double?,
            @SerializedName("evening")
            val evening: Evening?,
            @SerializedName("morning")
            val morning: Morning?,
            @SerializedName("night")
            val night: Night?,
            @SerializedName("night_short")
            val nightShort: NightShort?
        ) {
            data class Day(
                @SerializedName("cloudness")
                val cloudness: Double?,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp_avg")
                val tempAvg: Int?,
                @SerializedName("temp_max")
                val tempMax: Int?,
                @SerializedName("temp_min")
                val tempMin: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )

            data class DayShort(
                @SerializedName("cloudness")
                val cloudness: Double,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double?,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp")
                val temp: Int?,
                @SerializedName("temp_min")
                val tempMin: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )

            data class Evening(
                @SerializedName("cloudness")
                val cloudness: Double?,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double?,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp_avg")
                val tempAvg: Int?,
                @SerializedName("temp_max")
                val tempMax: Int?,
                @SerializedName("temp_min")
                val tempMin: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )

            data class Morning(
                @SerializedName("cloudness")
                val cloudness: Double?,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double?,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp_avg")
                val tempAvg: Int?,
                @SerializedName("temp_max")
                val tempMax: Int?,
                @SerializedName("temp_min")
                val tempMin: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )

            data class Night(
                @SerializedName("cloudness")
                val cloudness: Double?,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double?,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp_avg")
                val tempAvg: Int?,
                @SerializedName("temp_max")
                val tempMax: Int?,
                @SerializedName("temp_min")
                val tempMin: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )

            data class NightShort(
                @SerializedName("cloudness")
                val cloudness: Double?,
                @SerializedName("condition")
                val condition: String?,
                @SerializedName("daytime")
                val daytime: String?,
                @SerializedName("feels_like")
                val feelsLike: Int?,
                @SerializedName("fresh_snow_mm")
                val freshSnowMm: Double?,
                @SerializedName("humidity")
                val humidity: Int?,
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("polar")
                val polar: Boolean?,
                @SerializedName("prec_mm")
                val precMm: Double?,
                @SerializedName("prec_period")
                val precPeriod: Int?,
                @SerializedName("prec_prob")
                val precProb: Int?,
                @SerializedName("prec_strength")
                val precStrength: Double?,
                @SerializedName("prec_type")
                val precType: Int?,
                @SerializedName("pressure_mm")
                val pressureMm: Int?,
                @SerializedName("pressure_pa")
                val pressurePa: Int?,
                @SerializedName("soil_moisture")
                val soilMoisture: Double?,
                @SerializedName("soil_temp")
                val soilTemp: Int?,
                @SerializedName("_source")
                val source: String?,
                @SerializedName("temp")
                val temp: Int?,
                @SerializedName("uv_index")
                val uvIndex: Int?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_gust")
                val windGust: Double?,
                @SerializedName("wind_speed")
                val windSpeed: Double?
            )
        }
    }

    data class GeoObject(
        @SerializedName("country")
        val country: Country,
        @SerializedName("district")
        val district: Any?,
        @SerializedName("locality")
        val locality: Locality?,
        @SerializedName("province")
        val province: Province?
    ) {
        data class Country(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String
        )

        data class Locality(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?
        )

        data class Province(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?
        )
    }

    data class Info(
        @SerializedName("def_pressure_mm")
        val defPressureMm: Int?,
        @SerializedName("def_pressure_pa")
        val defPressurePa: Int?,
        @SerializedName("f")
        val f: Boolean?,
        @SerializedName("geoid")
        val geoid: Int?,
        @SerializedName("_h")
        val h: Boolean?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lon")
        val lon: Double?,
        @SerializedName("n")
        val n: Boolean?,
        @SerializedName("nr")
        val nr: Boolean?,
        @SerializedName("ns")
        val ns: Boolean?,
        @SerializedName("nsr")
        val nsr: Boolean?,
        @SerializedName("p")
        val p: Boolean?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("tzinfo")
        val tzinfo: Tzinfo,
        @SerializedName("url")
        val url: String?,
        @SerializedName("zoom")
        val zoom: Int?
    ) {
        data class Tzinfo(
            @SerializedName("abbr")
            val abbr: String?,
            @SerializedName("dst")
            val dst: Boolean?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("offset")
            val offset: Int?
        )
    }

    data class Yesterday(
        @SerializedName("temp")
        val temp: Int?
    )
}