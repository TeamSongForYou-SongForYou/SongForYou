package com.hanyeop.songforyou.view.main.karaoke

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.usecase.kakao.GetSearchKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaraokeViewModel @Inject constructor(
    private val getSearchKeywordUseCase: GetSearchKeywordUseCase
): ViewModel() {

    fun getSearchKeyword(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            getSearchKeywordUseCase.execute(query).collectLatest {
                Log.d("test5", "getSearchKeyword: $it")
            }
        }
    }
}