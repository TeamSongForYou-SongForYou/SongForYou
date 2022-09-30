package com.hanyeop.songforyou.view.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class NextSongRecommendViewModel @Inject constructor(
    private val ibRecommendBeforeUseCase: GetIbRecommendBeforeUseCase
): ViewModel() {

    private val _beforeSongTitle : MutableStateFlow<String> = MutableStateFlow("")
    val beforeSongTitle get() = _beforeSongTitle.asStateFlow()

    private val _nextSongRecommendList: MutableStateFlow<List<SongResponse>> = MutableStateFlow(listOf())
    val nextSongRecommendList get() = _nextSongRecommendList.asStateFlow()

    fun getNextSongRecommend(songSeq : Int, beforeSongTitle : String){
        viewModelScope.launch(Dispatchers.IO) {
            ibRecommendBeforeUseCase.execute(songSeq).collectLatest {
                if(it is ResultType.Success){
                    _nextSongRecommendList.value = it.data.data
                    _beforeSongTitle.value = beforeSongTitle
                }
            }
        }
    }
}