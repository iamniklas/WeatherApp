package de.niklasenglmeier.weatherapp.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.niklasenglmeier.weatherapp.R
import de.niklasenglmeier.weatherapp.models.WeatherData
import java.util.*
import kotlin.math.roundToInt


class HomeRecyclerViewAdapter(var activity: Activity, var id: Int, var data: Array<WeatherData?>, context: Context) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(activity)
    private lateinit var clickListener: ItemClickListener

    private var ctx = context

    private enum class TEMPERATURE_UNIT { CELSIUS, FAHRENHEIT }
    private var temperatureUnit = TEMPERATURE_UNIT.CELSIUS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.weather_data_card, parent, false)
        return ViewHolder(id, view, ctx)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.temperatureC.text = "${data[position]!!.temperatureCelsius.roundToInt()}° C"
        holder.weatherStatus.text = data[position]!!.condition
        holder.cityName.text = data[position]!!.cityName
        val picasso = Picasso.get()
        picasso.isLoggingEnabled = true
        picasso.load("https:${data[position]!!.conditionIcon}").into(holder.statusIcon)

        holder.itemView.setOnLongClickListener {
            setPosition(holder.position)
            false
        }
    }

    override fun onViewRecycled(holder: HomeRecyclerViewAdapter.ViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): WeatherData {
        return data[position]!!
    }

    private var position = 0

    fun getPosition(): Int {
        return position
    }

    private fun setPosition(position: Int) {
        this.position = position
    }

    inner class ViewHolder(_viewId: Int, _view: View, _ctx: Context) : RecyclerView.ViewHolder(_view), View.OnClickListener, View.OnCreateContextMenuListener {
        private val viewId = _viewId
        private val view = _view
        private val cardview: CardView = view.findViewById(R.id.cardView_weather_card)

        var weatherStatus: TextView = view.findViewById(R.id.textView_weather_card_weather_status)
        var temperatureC: TextView = view.findViewById(R.id.textView_weather_card_temperature)
        var cityName: TextView = view.findViewById(R.id.textView_weather_card_city_name)
        var statusIcon: ImageView = view.findViewById(R.id.imageView_weather_card_weather_status)

        init {
            cardview.setOnClickListener(this)
            cardview.setOnCreateContextMenuListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(viewId, adapterPosition)
        }

        override fun onCreateContextMenu(contextMenu: ContextMenu?, view: View?, contextMenuInfo: ContextMenu.ContextMenuInfo?) {
            activity.menuInflater.inflate(R.menu.context_menu_main_recycler_view, contextMenu)
        }
    }

    fun setClickListener(itemClickListener: HomeFragment) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(id: Int, position: Int)
    }

}