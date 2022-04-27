package de.niklasenglmeier.weatherapp.services

import de.niklasenglmeier.weatherapp.models.WeatherData

interface IWeatherDataService {
    fun getWeatherData(cityName: String, onResultListener: OnResultListener<WeatherData>)
}