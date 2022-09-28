package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.WeatherApi
import com.hanyeop.songforyou.model.response.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {
   fun getWeather(dataType : String, numOfRows : Int, pageNo : Int,
        baseDate : String, baseTime : String, nx : Int, ny : Int
    ): Flow<Weather> = flow {
        emit(weatherApi.getWeather(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny))
   }
}