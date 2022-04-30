package de.niklasenglmeier.weatherapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.niklasenglmeier.weatherapp.SharedPreferences
import de.niklasenglmeier.weatherapp.databinding.FragmentLocationBinding
import de.niklasenglmeier.weatherapp.ui.FragmentReloader

class LocationFragment : Fragment(), LocationRecyclerViewAdapter.ItemClickListener, FragmentReloader {

    private var _binding: FragmentLocationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var locations: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        locations = SharedPreferences.getSavedLocations(requireContext()).toMutableList()

        val recyclerView = binding.recyclerViewLocation
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = LocationRecyclerViewAdapter(requireActivity(), 0, locations, requireContext())
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: Int, position: Int) {
        locations.removeAt(position)

        SharedPreferences.saveLocations(requireContext(), locations)
        onReload()
    }

    override fun onReload() {
        locations = SharedPreferences.getSavedLocations(requireContext()).toMutableList()

        val recyclerView = binding.recyclerViewLocation
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = LocationRecyclerViewAdapter(requireActivity(), 0, locations, requireContext())
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
    }
}