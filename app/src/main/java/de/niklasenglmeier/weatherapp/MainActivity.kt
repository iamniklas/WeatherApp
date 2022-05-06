package de.niklasenglmeier.weatherapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.niklasenglmeier.weatherapp.databinding.ActivityMainBinding
import de.niklasenglmeier.weatherapp.models.LocationData
import de.niklasenglmeier.weatherapp.services.OnResultListener
import de.niklasenglmeier.weatherapp.services.implementation.WeatherDataService
import de.niklasenglmeier.weatherapp.ui.FragmentReloader
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var locations: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locations = SharedPreferences.getSavedLocations(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_location
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        navView.setupWithNavController(navController)

        binding.floatingActionButton.setOnClickListener { makeDialog() }
    }

    private fun makeDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("New Location")

        val input = EditText(this)
        input.hint = "Location..."
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->

            WeatherDataService().getLocationData(applicationContext, input.editableText.toString(), object : OnResultListener<LocationData> {
                override fun onSuccess(result: LocationData) {
                    val resultAlertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                    resultAlertDialogBuilder.apply {
                        setTitle("Is this what you are looking for?")
                        setMessage("City: ${result.cityName}\nRegion: ${result.region}\nCountry: ${result.country}")
                        setPositiveButton("Correct") { p0, p1 ->
                            locations.add(input.editableText.toString())
                            SharedPreferences.saveLocations(applicationContext, locations)
                            doFragmentReload()
                        }
                        setNegativeButton("No", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) { }
                        })
                    }
                    resultAlertDialogBuilder.show()
                }

                override fun onFailure(exception: Exception) {
                    Toast.makeText(applicationContext, "Failed to fetch location data for \"${input.editableText}\"", Toast.LENGTH_LONG).show()
                }
            })
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun doFragmentReload() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        (navHostFragment.childFragmentManager.fragments[0] as FragmentReloader).onReload()
    }
}