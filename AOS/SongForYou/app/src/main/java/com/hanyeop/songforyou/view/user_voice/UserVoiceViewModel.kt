package com.hanyeop.songforyou.view.user_voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.usecase.my_page.UploadUserVoiceUseCase
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UserVoiceViewModel @Inject constructor(
    private val uploadUserVoiceUseCase: UploadUserVoiceUseCase
): ViewModel() {

    private val _successMsgEvent = SingleLiveEvent<String>()
    val successMsgEvent get() = _successMsgEvent

    private val _failMsgEvent = SingleLiveEvent<String>()
    val failMsgEvent get() = _failMsgEvent

    fun uploadUserVoice(userSeq: Int, recordFile: MultipartBody.Part) {
        viewModelScope.launch(Dispatchers.IO) {
            uploadUserVoiceUseCase.execute(userSeq, recordFile).collectLatest {
                if (it is ResultType.Success) {
                    _successMsgEvent.postValue("성공")
                } else if (it is ResultType.Fail) {
                    _failMsgEvent.postValue("실패")
                }
            }
        }
    }
}