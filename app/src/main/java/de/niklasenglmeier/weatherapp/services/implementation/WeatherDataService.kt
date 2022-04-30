package de.niklasenglmeier.weatherapp.services.implementation

import android.content.Context
import android.os.Bundle
import com.android.volley.BuildConfig
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import de.niklasenglmeier.weatherapp.models.WeatherData
import de.niklasenglmeier.weatherapp.services.IWeatherDataService
import de.niklasenglmeier.weatherapp.services.OnResultListener
import java.util.*

class WeatherDataService : IWeatherDataService {
    override fun getWeatherData(context: Context, cityName: String, onResultListener: OnResultListener<WeatherData>) {
        val queue = Volley.newRequestQueue(context)

        queue.add(JsonObjectRequest("https://api.weatherapi.com/v1/current.json?key=${de.niklasenglmeier.weatherapp.BuildConfig.API_KEY}&q=${cityName}&aqi=no",
            {
                Firebase.analytics.logEvent("weather_data_fetch") {
                    param("city_name", cityName)
                    param("current_time_millis", System.currentTimeMillis())
                }
                onResultListener.onSuccess(WeatherData.fromJsonObject(it))
            },
            {
                Firebase.analytics.logEvent("weather_data_fetch_error") {
                    param("city_name", cityName)
                    param("current_time_millis", System.currentTimeMillis())
                }
                onResultListener.onFailure(it)
            }
        ))
    }

}