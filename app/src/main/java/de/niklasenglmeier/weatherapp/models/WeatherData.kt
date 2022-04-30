package de.niklasenglmeier.weatherapp.models

import org.json.JSONObject

data class WeatherData(val cityName: String,
                       val region: String,
                       val country: String,
                       val lastUpdated: Long,
                       val temperatureCelsius: Float,
                       val temperatureFeelsLike: Float,
                       val windSpeedInKph: Float,
                       val isDay: Boolean,
                       val condition: String,
                       val conditionIcon: String,
                       val windDirection: String,
                       val visibilityInKm: Float,
                       val humidity: Int,
) {

    companion object {
        fun fromJsonObject(jsonObject: JSONObject): WeatherData {
            return WeatherData(
                jsonObject.getJSONObject("location").getString("name"),
                jsonObject.getJSONObject("location").getString("region"),
                jsonObject.getJSONObject("location").getString("country"),
                jsonObject.getJSONObject("current").getLong("last_updated_epoch"),
                jsonObject.getJSONObject("current").getDouble("temp_c").toFloat(),
                jsonObject.getJSONObject("current").getDouble("feelslike_c").toFloat(),
                jsonObject.getJSONObject("current").getDouble("wind_kph").toFloat(),
                isDay = jsonObject.getJSONObject("current").getInt("is_day") == 1,
                jsonObject.getJSONObject("current").getJSONObject("condition").getString("text"),
                jsonObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
                jsonObject.getJSONObject("current").getString("wind_dir"),
                jsonObject.getJSONObject("current").getDouble("vis_km").toFloat(),
                jsonObject.getJSONObject("current").getInt("humidity")
            )
        }
    }
}
