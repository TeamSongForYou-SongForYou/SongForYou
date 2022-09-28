package com.hanyeop.songforyou.view.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.model.response.Weather
import com.hanyeop.songforyou.usecase.ib_recommend.GetIbRecommendMyListUseCase
import com.hanyeop.songforyou.usecase.weather.GetWeatherUseCase
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getIbRecommendMyListUseCase: GetIbRecommendMyListUseCase
): ViewModel() {

    private val _recommendMyList: MutableStateFlow<List<SongResponse>> = MutableStateFlow(listOf())
    val recommendMyList get() = _recommendMyList.asStateFlow()

    private val _weatherResponse : MutableStateFlow<ResultType<Weather>> = MutableStateFlow(ResultType.Uninitialized)
    val weatherResponse get() = _weatherResponse.asStateFlow()

    fun getWeather(dataType : String, numOfRows : Int, pageNo : Int,
                   baseDate : String, baseTime : String, nx : Int, ny : Int){
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.execute(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny).collectLatest {
                Log.d("test5", "getWeather: $it")
                _weatherResponse.value = it
            }
        }
    }

    fun getIbRecommendMyList(){
        viewModelScope.launch(Dispatchers.IO) {
            getIbRecommendMyListUseCase.execute().collectLatest {
                if(it is ResultType.Success){
                    _recommendMyList.value = it.data.data
                }
            }
        }
    }
}