package com.hanyeop.songforyou.view.user_voice

import android.util.Log
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
                Log.d("test5", "uploadUserVoice: $it")
                if (it is ResultType.Success) {
                    _successMsgEvent.postValue("사용자 음성 정보가 등록되었습니다.")
                } else if (it is ResultType.Fail) {
//                    _failMsgEvent.postValue("")
                }
            }
        }
    }
}