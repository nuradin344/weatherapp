package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val service: Api) : ViewModel() {
    private val dataForecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast>
        get() = dataForecast

    fun loadData() = runBlocking {
        launch {
            dataForecast.value = service.getForecast()
        }
    }
}