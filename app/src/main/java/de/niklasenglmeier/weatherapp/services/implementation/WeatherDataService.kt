package de.niklasenglmeier.weatherapp.services.implementation

import android.content.Context
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import de.niklasenglmeier.weatherapp.models.LocationData
import de.niklasenglmeier.weatherapp.models.WeatherData
import de.niklasenglmeier.weatherapp.services.IWeatherDataService
import de.niklasenglmeier.weatherapp.services.OnResultListener

class WeatherDataService : IWeatherDataService {
    override fun getLocationData(context: Context, cityName: String, onResultListener: OnResultListener<LocationData>) {
        val queue = Volley.newRequestQueue(context)

        queue.add(JsonObjectRequest("https://api.weatherapi.com/v1/current.json?key=${de.niklasenglmeier.weatherapp.BuildConfig.API_KEY}&q=${cityName}&aqi=no",
            {
                val locationData = LocationData.fromJsonObject(it)
                Firebase.analytics.logEvent("location_data_fetch") {
                    param("city_name", cityName)
                    param("city_name_found", locationData.cityName)
                    param("region_name_found", locationData.region)
                    param("country_name_found", locationData.country)
                    param("current_time_millis", System.currentTimeMillis())
                }
                onResultListener.onSuccess(locationData)
            },
            {
                Firebase.analytics.logEvent("location_data_fetch_error") {
                    param("city_name", cityName)
                    param("current_time_millis", System.currentTimeMillis())
                }
                onResultListener.onFailure(it)
            }
        ))
    }

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