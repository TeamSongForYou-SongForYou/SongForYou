package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.model.response.Weather
import com.hanyeop.songforyou.utils.weatherApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("getUltraSrtNcst?serviceKey=${weatherApiKey}")
    suspend fun getWeather(
        @Query("dataType") dataType : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String,
        @Query("nx") nx : Int,
        @Query("ny") ny : Int
    ) : Weather
}