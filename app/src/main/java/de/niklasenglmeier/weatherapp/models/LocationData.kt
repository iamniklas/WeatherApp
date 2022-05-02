package de.niklasenglmeier.weatherapp.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class LocationData(val cityName: String, val region: String, val country: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityName)
        parcel.writeString(region)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<LocationData> {
            override fun createFromParcel(parcel: Parcel): LocationData {
                return LocationData(parcel)
            }

            override fun newArray(size: Int): Array<LocationData?> {
                return arrayOfNulls(size)
            }
        }

        fun fromJsonObject(jsonObject: JSONObject): LocationData {
            return LocationData(
                jsonObject.getJSONObject("location").getString("name"),
                jsonObject.getJSONObject("location").getString("region"),
                jsonObject.getJSONObject("location").getString("country"),
            )
        }
    }

}
