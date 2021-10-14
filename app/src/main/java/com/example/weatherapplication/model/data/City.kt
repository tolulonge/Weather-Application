package com.example.weatherapplication.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * These are the model classes coming from the api response
 */
@Parcelize
data class City(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    var image : String
): Parcelable








