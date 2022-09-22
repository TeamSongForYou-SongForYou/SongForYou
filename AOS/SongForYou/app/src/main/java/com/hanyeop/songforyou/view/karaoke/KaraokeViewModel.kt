package com.hanyeop.songforyou.view.karaoke

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.Place
import com.hanyeop.songforyou.usecase.kakao.GetSearchKeywordUseCase
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaraokeViewModel @Inject constructor(
    private val getSearchKeywordUseCase: GetSearchKeywordUseCase,
): ViewModel() {

    private val _karaokeList: MutableStateFlow<List<Place>> = MutableStateFlow(listOf())
    val karaokeList get() = _karaokeList.asStateFlow()

    fun getSearchKeyword(query: String, x: String, y: String){
        viewModelScope.launch(Dispatchers.IO) {
            getSearchKeywordUseCase.execute(query, x, y).collectLatest {
                Log.d("test5", "getSearchKeyword: $it")
                if(it is ResultType.Success){
                    _karaokeList.value = it.data.documents
                }
            }
        }
    }
}