package com.example.weatherapplication.di

import com.example.weatherapplication.model.network.OpenWeatherEndpoint
import com.example.weatherapplication.model.repository.CityRepository
import com.example.weatherapplication.model.repository.CityRepositoryImpl
import com.example.weatherapplication.utils.API_KEY
import com.example.weatherapplication.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiServiceEndPoint(): OpenWeatherEndpoint {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        // Creates an implementation of the ApiService
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().addInterceptor(logging).build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun provideApiKey() : String = API_KEY


    @Singleton
    @Provides
    fun provideCityRepository(openWeatherEndpoint: OpenWeatherEndpoint, apiKey : String): CityRepository{
        return CityRepositoryImpl(openWeatherEndpoint, apiKey)
    }

}