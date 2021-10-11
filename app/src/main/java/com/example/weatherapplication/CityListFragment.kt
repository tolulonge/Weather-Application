package com.example.weatherapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.databinding.FragmentCityListBinding
import com.example.weatherapplication.model.data.City
import com.example.weatherapplication.model.data.CityData
import com.example.weatherapplication.ui.WeatherAdapter
import com.example.weatherapplication.utils.getGreetingImage
import com.example.weatherapplication.viewmodel.CityViewModel
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weatherAdapter = createAdapter(arrayListOf())
        cityViewModel.getListOfCity()
        setupRecyclerView(weatherAdapter)
        observeViewModel(weatherAdapter)

        binding.greetingImg.setImageResource(getGreetingImage())

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // The drawing view is the id of the view above which the container transform
            // will play in z-space.
            drawingViewId = R.id.nav_host_fragment_container
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            // Set the color of the scrim to transparent as we also want to animate the
            // list fragment out of view
            scrimColor = Color.TRANSPARENT
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observeViewModel(weatherAdapter: WeatherAdapter) {
        cityViewModel.cityDetails.observe(viewLifecycleOwner) {
            if (it.size >= CityData.listOfCityName.size) {
                weatherAdapter.setupList(it)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun createAdapter(citiesList: ArrayList<City>): WeatherAdapter {
        return WeatherAdapter(citiesList) { view, city ->
            val toCityDetail =
                CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(city)

            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(
                    R.integer.reply_motion_duration_large
                ).toLong()
            }

            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(
                    R.integer.reply_motion_duration_large
                ).toLong()
            }

            val cityDetailTransitionName = getString(
                R.string.city_detail_transition_name
            )

            val extraInfoForSharedElement = FragmentNavigatorExtras(
                view to cityDetailTransitionName
            )

            navigate(toCityDetail, extraInfoForSharedElement)
        }
    }

    private fun setupRecyclerView(weatherAdapter: WeatherAdapter) {
        binding.cityRecyclerView.run {
            adapter = weatherAdapter
        }
    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) =
        with(findNavController()) {
            currentDestination?.getAction(destination.actionId)
                ?.let { navigate(destination, extraInfo) }
        }
}