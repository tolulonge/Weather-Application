package com.example.weatherapplication.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapplication.R
import java.text.SimpleDateFormat
import java.util.*

fun getGreetingImage(): Int {
    val c = Calendar.getInstance()

    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> R.drawable.good_morning
        in 12..15 -> R.drawable.good_afternoon
        in 16..20 -> R.drawable.good_evening
        in 21..23 -> R.drawable.good_night
        else -> R.drawable.hi_there
    }
}

fun getCalendarDate(): String {
    val c = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("EEE MM dd, yyyy", Locale.ROOT)
    return dateFormat.format(c.time)
}

fun getApplicationTheme(): Int {
    val c = Calendar.getInstance()

    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> R.style.Theme_WeatherApplication
        in 12..15 -> R.style.Theme_WeatherApplication_GoodAfternoon
        in 16..20 -> R.style.Theme_WeatherApplication_GoodEvening
        in 21..23 -> R.style.Theme_WeatherApplication_GoodNight
        else -> R.style.Theme_WeatherApplication_GoodAfternoon
    }
}

fun getMainViewColor(): Int {
    val c = Calendar.getInstance()

    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> R.color.good_morning
        in 12..15 -> R.color.good_afternoon
        in 16..20 -> R.color.good_evening
        in 21..23 -> R.color.good_night
        else -> R.color.good_afternoon
    }
}


fun getWeatherGifToLoad(weatherName: String): Int {
    return when (weatherName) {
        "Clouds" -> R.drawable.clouds_gif
        "Rain" -> R.drawable.rainy
        "Mist" -> R.drawable.mist_gif
        "Clear" -> R.drawable.clear_gif
        "Haze" -> R.drawable.haze_gif
        else -> R.drawable.clear_gif
    }

}


fun ImageView.load(imageAddress: String, onClick: (() -> Unit)? = null) {
    val imageView = this
    Glide.with(this)
        .load(imageAddress).apply(
            RequestOptions()
                .placeholder(R.drawable.loading_status_animation)
                .error(R.drawable.ic_error_image)
        )
        .into(imageView.apply {
            setOnClickListener {
                onClick?.invoke()
            }

        })
}