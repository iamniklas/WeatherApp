package de.niklasenglmeier.weatherapp.services

import android.content.Context
import de.niklasenglmeier.weatherapp.models.LocationData
import de.niklasenglmeier.weatherapp.models.WeatherData

interface IWeatherDataService {
    fun getLocationData(context: Context, cityName: String, onResultListener: OnResultListener<LocationData>)
    fun getWeatherData(context: Context, cityName: String, onResultListener: OnResultListener<WeatherData>)
}