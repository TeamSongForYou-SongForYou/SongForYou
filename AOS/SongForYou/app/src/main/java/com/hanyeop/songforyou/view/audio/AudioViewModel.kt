package com.hanyeop.songforyou.view.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.usecase.song_box.UploadRecordUseCase
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val uploadRecordUseCase: UploadRecordUseCase
):ViewModel() {

    private val _successMsgEvent = SingleLiveEvent<String>()
    val successMsgEvent get() = _successMsgEvent

    private val _failMsgEvent = SingleLiveEvent<String>()
    val failMsgEvent get() = _failMsgEvent

    fun uploadRecord(songSeq: Int, recordFile: MultipartBody.Part){
        viewModelScope.launch(Dispatchers.IO) {
            uploadRecordUseCase.execute(songSeq, recordFile).collectLatest {
                if(it is ResultType.Success){
                    _successMsgEvent.postValue("성공")
                }else{
                    _failMsgEvent.postValue("실패")
                }
            }
        }
    }
}