package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapplication.databinding.FragmentCityDetailBinding
import com.example.weatherapplication.utils.getCalendarDate
import com.example.weatherapplication.utils.getMainViewColor
import com.example.weatherapplication.utils.getWeatherGifToLoad
import com.example.weatherapplication.utils.load
import dagger.hilt.android.AndroidEntryPoint

/**
 * This fragment class holds the views for displaying a single particular city
 */
@AndroidEntryPoint
class CityDetailFragment : Fragment() {

    private var _binding: FragmentCityDetailBinding? = null

    private val binding get() = _binding!!
    private val args: CityDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieves the args from navigation
        val city = args.city

        // Loads the image of the city into the image view
        binding.cityImageVIew.apply {
           load(city.image)
        }
        binding.cardView.setCardBackgroundColor(resources.getColor(getMainViewColor()))
        val weatherGif = getWeatherGifToLoad(city.weather[0].main)
        Glide.with(requireContext())
            .load(weatherGif)
            .into(binding.weatherGifImg)
        binding.cityTemperature.text = "${(city.main.temp.toInt() - 273)}℃"
        binding.txtCityName.text = city.name
        binding.txtDate.text = getCalendarDate()
        binding.txtMainWeather.text = city.weather[0].main
        binding.txtHumidityValue.text = city.main.humidity.toString()
        binding.txtPressureValue.text = city.main.pressure.toString()
        binding.txtWindValue.text = "${city.wind.speed} Km/hr"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}