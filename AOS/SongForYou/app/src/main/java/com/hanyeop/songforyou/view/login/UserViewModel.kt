package com.hanyeop.songforyou.view.login

import android.content.SharedPreferences
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.repository.Oauth2Repository
import com.hanyeop.songforyou.repository.UserRepository
import com.hanyeop.songforyou.utils.Event
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserViewModel___"

@HiltViewModel
class UserViewModel @Inject constructor(
    private val oauth2Repository: Oauth2Repository,
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository
): ViewModel() {

    // viewModel에서 Toast 메시지 띄우기 위한 Event
    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = _message

    private val _email_verification_code = MutableStateFlow("")
    val email_verification_code get() = _email_verification_code.asStateFlow()

    private val _errorMsgEvent = SingleLiveEvent<String>()
    val errorMsgEvent get() = _errorMsgEvent

    private val _failMsgEvent = SingleLiveEvent<String>()
    val failMsgEvent get() = _failMsgEvent

    private val _joinMsgEvent = SingleLiveEvent<String>()
    val joinMsgEvent get() = _joinMsgEvent

    private val _fcmEvent = SingleLiveEvent<String>()
    val fcmEvent : LiveData<String> get() = _fcmEvent

    // 이메일 중복 검사
    fun checkEmail(userEmail: String, textInputLayout: TextInputLayout){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkEmail(userEmail).collectLatest {
                if(it is ResultType.Success){
                    if(it.data.data.equals(true)){
                        true
                    }else{
                        Log.d(TAG, "${it.data}")
                        makeTextInputLayoutError(textInputLayout, "중복된 이메일입니다")
                        makeToast("중복된 이메일입니다")
                        false
                    }

                }else if(it is ResultType.Error){
                    _errorMsgEvent.postValue("서버 에러 발생")
                }

            }
        }
    }

    // 닉네임 중복 검사
    //fun
    // 이메일 인증번호 전송
    // 이메일 인증번호 확인
    // 비밀번호 찾기

    // 일반 회원기입
    fun signUpUser(token: String, userDto: UserDto){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.signUpUser(token, userDto).collectLatest {
                if(it is ResultType.Success) {

                }
            }
        }
    }

    // 일반 로그인


    fun makeToast(msg: String) {
        _message.value = Event(msg)
    }

    fun makeTextInputLayoutError(textInputLayout: TextInputLayout, msg: String) {
        textInputLayout.error = msg
        textInputLayout.isErrorEnabled = true
    }
}