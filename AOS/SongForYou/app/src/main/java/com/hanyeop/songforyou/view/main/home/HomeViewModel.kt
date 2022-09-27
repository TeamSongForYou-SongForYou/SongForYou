package com.hanyeop.songforyou.view.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.usecase.ib_recommend.GetIbRecommendBeforeUseCase
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
    private val getIbRecommendBeforeUseCase: GetIbRecommendBeforeUseCase
): ViewModel() {

    private val _recommendList = MutableStateFlow<List<SongResponse>>(listOf())
    val recommendList get() = _recommendList.asStateFlow()

    fun getIbRecommendBefore(songSeq: Int){
        Log.d("test5", "getIbRecommendBefore: ???")
        viewModelScope.launch(Dispatchers.IO) {
            getIbRecommendBeforeUseCase.execute(songSeq).collectLatest {
                Log.d("test5", "getIbRecommendBefore: ${it}")
                if(it is ResultType.Success){
                    _recommendList.value = it.data.data
                }else{

                }
            }
        }
    }
}