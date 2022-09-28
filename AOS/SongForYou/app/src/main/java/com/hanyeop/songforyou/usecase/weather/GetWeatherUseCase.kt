package com.hanyeop.songforyou.usecase.weather

import com.hanyeop.songforyou.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
){
    fun execute(dataType : String, numOfRows : Int, pageNo : Int,
                baseDate : String, baseTime : String, nx : Int, ny : Int)
    = weatherRepository.getWeather(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny)
}