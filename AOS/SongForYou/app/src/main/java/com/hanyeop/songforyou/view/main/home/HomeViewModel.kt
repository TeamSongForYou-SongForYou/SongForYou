package com.hanyeop.songforyou.view.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.Weather
import com.hanyeop.songforyou.usecase.sb_recommend.GetSbRecommendRandomUseCase
import com.hanyeop.songforyou.usecase.weather.GetWeatherUseCase
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
private const val TAG = "HomeViewModel___"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getSbRecommendRandomUseCase: GetSbRecommendRandomUseCase
): ViewModel() {

    private val _weatherResponse : MutableStateFlow<ResultType<Weather>> = MutableStateFlow(ResultType.Uninitialized)
    val weatherResponse get() = _weatherResponse.asStateFlow()

    private val _randomList : MutableStateFlow<ResultType<>>
    val randomList get() = _randomList.asStateFlow()

    fun getWeather(dataType : String, numOfRows : Int, pageNo : Int,
                   baseDate : String, baseTime : String, nx : Int, ny : Int){
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.execute(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny).collectLatest {
                Log.d("test5", "getWeather: $it")
                _weatherResponse.value = it
            }
        }
    }

    fun getSbRecommendRandom(){
        viewModelScope.launch(Dispatchers.IO) {
            getSbRecommendRandomUseCase.execute().collectLatest {
                Log.d(TAG, "getSbRecommendRandom$it")
            }
        }
    }
}