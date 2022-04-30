package de.niklasenglmeier.weatherapp.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class WeatherData(val cityName: String,
                       val region: String,
                       val country: String,
                       val lastUpdated: Long,
                       val temperatureCelsius: Float,
                       val temperatureFeelsLike: Float,
                       val windSpeedInKph: Float,
                       val isDay: Boolean,
                       val condition: String,
                       val conditionIcon: String,
                       val windDirection: String,
                       val visibilityInKm: Float,
                       val humidity: Int,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityName)
        parcel.writeString(region)
        parcel.writeString(country)
        parcel.writeLong(lastUpdated)
        parcel.writeFloat(temperatureCelsius)
        parcel.writeFloat(temperatureFeelsLike)
        parcel.writeFloat(windSpeedInKph)
        parcel.writeByte(if (isDay) 1 else 0)
        parcel.writeString(condition)
        parcel.writeString(conditionIcon)
        parcel.writeString(windDirection)
        parcel.writeFloat(visibilityInKm)
        parcel.writeInt(humidity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<WeatherData> {
            override fun createFromParcel(parcel: Parcel): WeatherData {
                return WeatherData(parcel)
            }

            override fun newArray(size: Int): Array<WeatherData?> {
                return arrayOfNulls(size)
            }
        }

        fun fromJsonObject(jsonObject: JSONObject): WeatherData {
            return WeatherData(
                jsonObject.getJSONObject("location").getString("name"),
                jsonObject.getJSONObject("location").getString("region"),
                jsonObject.getJSONObject("location").getString("country"),
                jsonObject.getJSONObject("current").getLong("last_updated_epoch"),
                jsonObject.getJSONObject("current").getDouble("temp_c").toFloat(),
                jsonObject.getJSONObject("current").getDouble("feelslike_c").toFloat(),
                jsonObject.getJSONObject("current").getDouble("wind_kph").toFloat(),
                isDay = jsonObject.getJSONObject("current").getInt("is_day") == 1,
                jsonObject.getJSONObject("current").getJSONObject("condition").getString("text"),
                jsonObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
                jsonObject.getJSONObject("current").getString("wind_dir"),
                jsonObject.getJSONObject("current").getDouble("vis_km").toFloat(),
                jsonObject.getJSONObject("current").getInt("humidity")
            )
        }
    }
}
