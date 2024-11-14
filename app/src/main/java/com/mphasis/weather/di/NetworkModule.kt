package com.mphasis.weather.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mphasis.weather.BuildConfig
import com.mphasis.weather.data.network.WeatherApi
import com.mphasis.weather.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Module that provides network-related dependencies for the application.
@Module
@InstallIn(SingletonComponent::class) // This module is installed in the SingletonComponent scope (application-wide)
object NetworkModule {

    // Provides an HttpLoggingInterceptor that logs HTTP request and response details.
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Provides an OkHttpClient that will handle the actual HTTP requests.
    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        // In debug mode, add the logging interceptor to log HTTP requests and responses.
        return if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(httpLoggingInterceptor).build()
        } else {
            okHttpClient.build()
        }
    }

    // Provides a Retrofit instance, which is used for making API requests.
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Set the base URL for the API
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // Convert JSON to Kotlin objects using Gson
        .client(okHttpClient) // Use the OkHttpClient for the network requests
        .build()

    // Provides an instance of WeatherApi, which is the service interface for making weather API requests.
    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit
        .create(WeatherApi::class.java)
}
