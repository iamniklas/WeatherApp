package de.niklasenglmeier.weatherapp

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Fade
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Fade()
            exitTransition = Fade()
            sharedElementEnterTransition = AutoTransition()
            sharedElementExitTransition = AutoTransition()
        }

        setContentView(R.layout.activity_details)

        findViewById<TextView>(R.id.textView_acdetails_city_name).text = intent.getStringExtra("city_name")
        findViewById<TextView>(R.id.textView_acdetails_status).text = intent.getStringExtra("weather_status")
        findViewById<TextView>(R.id.textView_acdetails_temp).text = "${intent.getStringExtra("temperature_c")}° C"
        Picasso.get().load("https:${intent.getStringExtra("weather_status_icon")}").into(findViewById<ImageView>(R.id.imageView_acdetails_status))
        findViewById<TextView>(R.id.textView_acdetails_feelsLike).text = intent.getStringExtra("temperature_feels_like")
        findViewById<TextView>(R.id.textView_acdetails_windSpeed).text = intent.getStringExtra("wind_speed")
        findViewById<TextView>(R.id.textView_acdetails_windDirection).text = intent.getStringExtra("wind_direction")
        findViewById<TextView>(R.id.textView_acdetails_visibility).text = intent.getStringExtra("visibility")
        findViewById<TextView>(R.id.textView_acdetails_humidity).text = intent.getStringExtra("humidity")
    }
}