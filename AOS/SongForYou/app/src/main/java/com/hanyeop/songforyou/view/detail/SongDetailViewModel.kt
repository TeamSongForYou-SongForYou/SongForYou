package com.hanyeop.songforyou.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.usecase.song.SongDisLikeUseCase
import com.hanyeop.songforyou.usecase.song_box.AddSongBoxUseCase
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    private val songDisLikeUseCase: SongDisLikeUseCase,
    private val addSongBoxUseCase: AddSongBoxUseCase
): ViewModel() {

    private val _successMsgEvent = SingleLiveEvent<String>()
    val successMsgEvent get() = _successMsgEvent

    private val _failMsgEvent = SingleLiveEvent<String>()
    val failMsgEvent get() = _failMsgEvent

    fun songDisLike(songSeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            songDisLikeUseCase.execute(songSeq).collectLatest {
                if(it is ResultType.Success){
                    _successMsgEvent.postValue("성공")
                }else{
                    _failMsgEvent.postValue("실패")
                }
            }
        }
    }

    fun addSongBox(songSeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            addSongBoxUseCase.execute(songSeq).collectLatest {
                if(it is ResultType.Success){
                    _successMsgEvent.postValue("성공")
                }else{
                    _failMsgEvent.postValue("실패")
                }
            }
        }
    }
}