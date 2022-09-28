package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.datasource.WeatherRemoteDataSource
import com.hanyeop.songforyou.model.response.Weather
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {
    fun getWeather(dataType : String, numOfRows : Int, pageNo : Int,
                   baseDate : String, baseTime : String, nx : Int, ny : Int
    ): Flow<ResultType<Weather>> = flow {
        emit(ResultType.Loading)
        weatherRemoteDataSource.getWeather(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny).collect {
            emit(ResultType.Success(it))
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}
