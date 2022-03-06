package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, ForecastActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.currentConditions.observe(this) { currentConditions ->
            bindData(currentConditions)
        }
        viewModel.loadData()
    }


    private fun bindData(currentConditions: CurrentConditions) {
        binding.CityName.text = currentConditions.name
        binding.Temperature.text =
            getString(R.string.Temperature, currentConditions.main.temp.toInt())
        binding.Feelslike.text =
            getString(R.string.Feelslike, currentConditions.main.feelsLike.toInt())
        binding.Low.text = getString(R.string.Low, currentConditions.main.tempMin.toInt())
        binding.High.text = getString(R.string.High, currentConditions.main.tempMax.toInt())
        binding.Humidity.text =
            getString(R.string.Humidity_tem, currentConditions.main.humidity.toInt())
        binding.Pressure.text =
            getString(R.string.Pressure, currentConditions.main.pressure.toInt())

        val weather = currentConditions.weather.firstOrNull()
        weather?.let {
            Glide.with(this)
                .load("https://openweathermap.org/img/wn/${it.icon}@2x.png")
                .into(binding.conditionIcon)
        }
    }


}