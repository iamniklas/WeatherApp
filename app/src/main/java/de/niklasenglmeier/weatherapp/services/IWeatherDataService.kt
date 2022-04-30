package de.niklasenglmeier.weatherapp.services

import android.content.Context
import de.niklasenglmeier.weatherapp.models.WeatherData

interface IWeatherDataService {
    fun getWeatherData(context: Context, cityName: String, onResultListener: OnResultListener<WeatherData>)
}