package com.example.weatherapplication.model.network

import com.example.weatherapplication.model.data.City
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherEndpoint {
    @GET("weather")
    suspend fun getCityDetails( @Query("q") cityName : String, @Query("appid") apiKey : String) : City
}