package de.niklasenglmeier.weatherapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import de.niklasenglmeier.weatherapp.DetailsActivity
import de.niklasenglmeier.weatherapp.SharedPreferences
import de.niklasenglmeier.weatherapp.databinding.FragmentHomeBinding
import de.niklasenglmeier.weatherapp.models.WeatherData
import de.niklasenglmeier.weatherapp.services.OnResultListener
import de.niklasenglmeier.weatherapp.services.implementation.WeatherDataService
import de.niklasenglmeier.weatherapp.ui.FragmentReloader
import kotlin.math.roundToInt


class HomeFragment : Fragment(), HomeRecyclerViewAdapter.ItemClickListener, FragmentReloader {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var completedTasks = 0

    private lateinit var weatherData: Array<WeatherData?>

    private lateinit var locations: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewFragmentHomeWeatherCards.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        locations = SharedPreferences.getSavedLocations(requireContext())

        weatherData = arrayOfNulls(locations.size)
        for (i in locations.indices) {
            WeatherDataService().getWeatherData(requireContext(), locations[i], object : OnResultListener<WeatherData> {
                override fun onSuccess(result: WeatherData) {
                    weatherData[i] = result
                    onTaskCompleted()
                }

                override fun onFailure(exception: Exception) {

                }
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        completedTasks = 0
    }

    override fun onItemClick(id: Int, position: Int) {
        val data = weatherData[position]
        val cityName = androidx.core.util.Pair<View, String>((binding.recyclerViewFragmentHomeWeatherCards.findViewHolderForAdapterPosition(position) as HomeRecyclerViewAdapter.ViewHolder).cityName, "city_name")
        val temperature = androidx.core.util.Pair<View, String>((binding.recyclerViewFragmentHomeWeatherCards.findViewHolderForAdapterPosition(position) as HomeRecyclerViewAdapter.ViewHolder).temperatureC, "temperature_c")
        val status = androidx.core.util.Pair<View, String>((binding.recyclerViewFragmentHomeWeatherCards.findViewHolderForAdapterPosition(position) as HomeRecyclerViewAdapter.ViewHolder).weatherStatus, "weather_status")
        val statusIcon = androidx.core.util.Pair<View, String>((binding.recyclerViewFragmentHomeWeatherCards.findViewHolderForAdapterPosition(position) as HomeRecyclerViewAdapter.ViewHolder).statusIcon, "weather_status_icon")

        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("city_name", data!!.cityName)
        intent.putExtra("temperature_c", data.temperatureCelsius.roundToInt().toString())
        intent.putExtra("weather_status", data.condition)
        intent.putExtra("weather_status_icon", data.conditionIcon)
        intent.putExtra("temperature_feels_like", "${data.temperatureFeelsLike.roundToInt()}° C")
        intent.putExtra("wind_speed", "${data.windSpeedInKph} kph")
        intent.putExtra("wind_direction", data.windDirection)
        intent.putExtra("visibility", "${data.visibilityInKm} km")
        intent.putExtra("humidity", "${data.humidity} %")

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), cityName, temperature, status, statusIcon)

        startActivity(intent, options.toBundle())
    }

    private fun onTaskCompleted() {
        requireActivity().runOnUiThread {
            if(++completedTasks >= weatherData.size) {
                val adapter = HomeRecyclerViewAdapter(requireActivity(), 0, weatherData, requireContext())
                adapter.setClickListener(this)
                binding.recyclerViewFragmentHomeWeatherCards.adapter = adapter
            }
        }
    }

    override fun onReload() {
        completedTasks = 0
        locations = SharedPreferences.getSavedLocations(requireContext())

        weatherData = arrayOfNulls(locations.size)
        for (i in locations.indices) {
            WeatherDataService().getWeatherData(requireContext(), locations[i], object : OnResultListener<WeatherData> {
                override fun onSuccess(result: WeatherData) {
                    weatherData[i] = result
                    onTaskCompleted()
                }

                override fun onFailure(exception: Exception) {

                }
            })
        }
    }
}