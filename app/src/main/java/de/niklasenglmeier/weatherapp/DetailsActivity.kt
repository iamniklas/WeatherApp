package de.niklasenglmeier.weatherapp

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Fade
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import de.niklasenglmeier.weatherapp.models.WeatherData

class DetailsActivity : AppCompatActivity() {
    private lateinit var weatherData: WeatherData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Fade()
            exitTransition = Fade()
            sharedElementEnterTransition = AutoTransition()
            sharedElementExitTransition = AutoTransition()
        }

        weatherData = intent.getParcelableExtra("weather_data")!!

        setContentView(R.layout.activity_details)

        findViewById<TextView>(R.id.textView_acdetails_city_name).text = weatherData.cityName
        findViewById<TextView>(R.id.textView_acdetails_status).text = weatherData.condition
        findViewById<TextView>(R.id.textView_acdetails_temp).text = "${weatherData.temperatureCelsius}° C"
        Picasso.get().load("https:${weatherData.conditionIcon}").into(findViewById<ImageView>(R.id.imageView_acdetails_status))
        findViewById<TextView>(R.id.textView_acdetails_feelsLike).text = "${weatherData.temperatureFeelsLike}° C"
        findViewById<TextView>(R.id.textView_acdetails_windSpeed).text = "${weatherData.windSpeedInKph} kph"
        findViewById<TextView>(R.id.textView_acdetails_windDirection).text = weatherData.windDirection
        findViewById<TextView>(R.id.textView_acdetails_visibility).text = "${weatherData.visibilityInKm} km"
        findViewById<TextView>(R.id.textView_acdetails_humidity).text = "${weatherData.humidity} %"
    }
}