package de.niklasenglmeier.weatherapp.services.implementation

import android.content.Context
import com.android.volley.BuildConfig
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import de.niklasenglmeier.weatherapp.models.WeatherData
import de.niklasenglmeier.weatherapp.services.IWeatherDataService
import de.niklasenglmeier.weatherapp.services.OnResultListener

class WeatherDataService : IWeatherDataService {
    override fun getWeatherData(context: Context, cityName: String, onResultListener: OnResultListener<WeatherData>) {
        val queue = Volley.newRequestQueue(context)

        queue.add(JsonObjectRequest("https://api.weatherapi.com/v1/current.json?key=${de.niklasenglmeier.weatherapp.BuildConfig.API_KEY}&q=${cityName}&aqi=no",
            {
                onResultListener.onSuccess(WeatherData.fromJsonObject(it))
            },
            {
                onResultListener.onFailure(it)
            }
        ))
    }

}