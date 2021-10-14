package com.example.weatherapplication.model.network

import com.example.weatherapplication.model.data.City
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the interface that contacts the network endpoint, retrives the queries
 * @param q for the name of the city whose weather is to be displayed
 * @param appid for the apiKey
 */
interface OpenWeatherEndpoint {
    @GET("weather")
    suspend fun getCityDetails( @Query("q") cityName : String, @Query("appid") apiKey : String) : City
}