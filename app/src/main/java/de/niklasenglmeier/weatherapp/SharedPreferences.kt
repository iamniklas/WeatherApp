package de.niklasenglmeier.weatherapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object SharedPreferences {
    fun getSavedLocations(context: Context): MutableList<String> {
        val sharedPreferences = context.getSharedPreferences("de.niklasenglmeier.weatherapp", Context.MODE_PRIVATE)
        return Gson().fromJson(sharedPreferences.getString("saved_locations", "[]"), Array<String>::class.java).toMutableList()
    }

    fun saveLocations(context: Context, locations: MutableList<String>) {
        val editor = context.getSharedPreferences("de.niklasenglmeier.weatherapp", Context.MODE_PRIVATE).edit()
        editor.putString("saved_locations", Gson().toJson(locations))
        editor.apply()
    }
}