package de.niklasenglmeier.weatherapp.services.implementation

import de.niklasenglmeier.weatherapp.models.WeatherData
import de.niklasenglmeier.weatherapp.services.IWeatherDataService
import de.niklasenglmeier.weatherapp.services.OnResultListener

class WeatherDataService : IWeatherDataService {
    override fun getWeatherData(cityName: String, onResultListener: OnResultListener<WeatherData>) {
        TODO("Not yet implemented")
    }

}