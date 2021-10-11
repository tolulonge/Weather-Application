package com.example.weatherapplication.model.repository

import com.example.weatherapplication.model.data.City

interface CityRepository {
    suspend fun getCityDetails(cityName : String) : City
}