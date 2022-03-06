package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String = "55425",
        @Query("units") units: String = "imperial",
        @Query("appId") appId: String = "5f5132e9a5d96ca558c073c1661b2d13",
    ): CurrentConditions

    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String = "55425",
        @Query("units") units: String = "imperial",
        @Query("appId") appId: String = "5f5132e9a5d96ca558c073c1661b2d13",
        @Query("cnt") cnt: Int = 16
    ): Forecast
}
