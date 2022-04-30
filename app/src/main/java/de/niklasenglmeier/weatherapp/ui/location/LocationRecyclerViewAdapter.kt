package de.niklasenglmeier.weatherapp.ui.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.niklasenglmeier.weatherapp.R

class LocationRecyclerViewAdapter(var activity: Activity, var id: Int, var data: MutableList<String>, context: Context) : RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(activity)
    private lateinit var clickListener: ItemClickListener

    private var ctx = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.location_card, parent, false)

        return ViewHolder(id, view, ctx)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewLocationName.text = data[position]

        holder.buttonLocationDelete.setOnClickListener {
            clickListener.onItemClick(holder.buttonLocationDelete.id, position)
        }
    }

    override fun onViewRecycled(holder: LocationRecyclerViewAdapter.ViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): String {
        return data[position]
    }

    private var position = 0

    fun getPosition(): Int {
        return position
    }

    private fun setPosition(position: Int) {
        this.position = position
    }

    inner class ViewHolder(_viewId: Int, _view: View, _ctx: Context) : RecyclerView.ViewHolder(_view) {
        private val viewId = _viewId
        private val view = _view
        private val cardview: CardView = view.findViewById(R.id.cardView_location)

        val textViewLocationName: TextView = view.findViewById(R.id.textView_location_text)
        val buttonLocationDelete: Button = view.findViewById(R.id.button_location_delete)

        init {
            buttonLocationDelete.setOnClickListener {

            }
        }
    }

    fun setClickListener(itemClickListener: LocationFragment) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(id: Int, position: Int)
    }
}