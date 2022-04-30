package de.niklasenglmeier.weatherapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import java.net.URL

@RunWith(AndroidJUnit4::class)
class WeatherDataFetchTest {
    @Test
    fun fetchData() {
        val url = URL("https://api.weatherapi.com/v1/current.json?key=${BuildConfig.API_KEY}&q=London&aqi=no")
        val http: HttpURLConnection = url.openConnection() as HttpURLConnection
        println(http.responseCode.toString() + " " + http.responseMessage)
        assertEquals(200, http.responseCode)
        assertEquals("OK", http.responseMessage)
    }
}