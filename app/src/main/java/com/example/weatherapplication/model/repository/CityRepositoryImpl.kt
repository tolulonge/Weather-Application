package com.example.weatherapplication.model.repository

import com.example.weatherapplication.model.data.City
import com.example.weatherapplication.model.network.OpenWeatherEndpoint


/**
 * This repository class abstracts access to the API endpoint, provides a clean API for
 * data access to the rest of the application, manages queries and communicates with the
 * remote data source according to request from the viewModel
 */
class CityRepositoryImpl(
    private val openWeatherEndpoint: OpenWeatherEndpoint, private val apiKey : String
): CityRepository {


    override suspend fun getCityDetails(cityName : String): City {
        return openWeatherEndpoint.getCityDetails(cityName, apiKey)
    }
}