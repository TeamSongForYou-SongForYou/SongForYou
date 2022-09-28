package com.hanyeop.songforyou.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.usecase.ib_recommend.GetIbRecommendMyListUseCase
import com.hanyeop.songforyou.usecase.ib_recommend.GetIbRecommendMyRecordUseCase
import com.hanyeop.songforyou.usecase.sb_recommend.GetSbRecommendRandomUseCase
import com.hanyeop.songforyou.usecase.song.SongSearchUseCase
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
class SongSearchViewModel@Inject constructor(
    private val songSearchUseCase: SongSearchUseCase
): ViewModel() {

    private val _resultList: MutableStateFlow<List<SongResponse>> = MutableStateFlow(listOf())
    val resultList get() = _resultList.asStateFlow()

    fun songSearch(songName: String){
        viewModelScope.launch(Dispatchers.IO) {
            songSearchUseCase.execute(songName).collectLatest {
                if(it is ResultType.Success){
                    _resultList.value = it.data.data
                }
            }
        }
    }
}