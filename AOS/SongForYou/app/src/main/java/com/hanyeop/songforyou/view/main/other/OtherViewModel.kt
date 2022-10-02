package com.hanyeop.songforyou.view.main.other

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.model.response.WordResponse
import com.hanyeop.songforyou.usecase.sb_recommend.GetRecommendWithWordUseCase
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val getRecommendWithWordUseCase: GetRecommendWithWordUseCase
): ViewModel() {

    private val _wordList: MutableStateFlow<List<WordResponse>> = MutableStateFlow(listOf())
    val wordList get() = _wordList.asStateFlow()

    private val _songList: MutableStateFlow<List<SongResponse>> = MutableStateFlow(listOf())
    val songList get() = _songList.asStateFlow()

    fun getRecommendWithWord(listNum: Int){
        viewModelScope.launch(Dispatchers.IO) {
            getRecommendWithWordUseCase.execute(listNum).collectLatest {
                if(it is ResultType.Success){
                    _wordList.value = it.data.data.info
                    Log.d("test5", "getRecommendWithWord: ${wordList.value}")

                    _songList.value = it.data.data.songList
                }
            }
        }
    }
}