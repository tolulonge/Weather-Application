package com.example.weatherapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.model.data.City
import com.example.weatherapplication.model.data.CityData
import com.example.weatherapplication.model.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This viewmodel classes exposes a livedata containing the list of cites and helps make the network
 * call in a coroutine to retrieve the list of cities
 */
@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    private var _cityDetails = MutableLiveData<ArrayList<City>>()
    val cityDetails: LiveData<ArrayList<City>>
        get() = _cityDetails
    private val tempList = arrayListOf<City>()


    fun getListOfCity() {

        for (i in CityData.listOfCityName) {
            viewModelScope.launch {
                try {
                    val cityDetail = cityRepository.getCityDetails(i)
                    CityData.listOfCityAndImage[cityDetail.name]?.let {
                        cityDetail.image = it
                    }
                    if (tempList.size < CityData.listOfCityName.size) {
                        tempList.add(cityDetail)
                        _cityDetails.value = tempList
                    }


                } catch (ex: Exception) {

                }
            }
        }
    }


}