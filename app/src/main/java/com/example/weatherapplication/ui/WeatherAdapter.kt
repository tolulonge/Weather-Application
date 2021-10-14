package com.example.weatherapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.LayoutCityItemBinding
import com.example.weatherapplication.model.data.City
import com.example.weatherapplication.utils.load

/**
 * This is the adapter class that helps bind the data coming from the api into views that the user
 * can see and interact with.
 * It receives an arrayList of the cities and an implementation of the a listener to handle click
 * events
 */
class WeatherAdapter(
    private var citiesData: ArrayList<City>,
    private val listener: ((view: View, city: City) -> Unit)? = null
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {


    class ViewHolder(private val binding: LayoutCityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City, listener: ((view: View, city: City) -> Unit)?) {
            binding.apply {
                txtPokemonName.text = city.name
                txtLatitude.text = txtLatitude.resources.getString(
                    R.string.city_latitude,
                    city.coord.lat.toString()
                )
                txtLongitude.text = txtLatitude.resources.getString(
                    R.string.city_longitude,
                    city.coord.lon.toString()
                )
                txtWeatherDescription.text = city.weather[0].description
                txtTemperature.text = "${(city.main.temp.toInt() - 273)}\u2103"
            }



            with(binding.imgCity) {
                load(city.image) {
                    listener?.let {
                        it(this, city)
                    }
                }
                transitionName = city.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutCityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCity = citiesData[position]
        holder.bind(currentCity, listener)

    }

    override fun getItemCount(): Int {
        return citiesData.size
    }


    fun setupList(citiesList: ArrayList<City>) {
        this.citiesData.clear()
        this.citiesData = citiesList
        notifyDataSetChanged()
    }


}