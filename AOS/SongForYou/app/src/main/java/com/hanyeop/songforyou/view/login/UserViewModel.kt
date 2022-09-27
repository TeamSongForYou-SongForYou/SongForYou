package com.hanyeop.songforyou.view.login

import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.repository.Oauth2Repository
import com.hanyeop.songforyou.repository.UserRepository
import com.hanyeop.songforyou.usecase.user.*
import com.hanyeop.songforyou.utils.Event
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.regex.Pattern
import javax.inject.Inject

private const val TAG = "UserViewModel___"

@HiltViewModel
class UserViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val checkEmailUseCase: CheckEmailUseCase,
    private val checkNicknameUseCase: CheckNicknameUseCase,
    private val requestEmailUseCase: RequestEmailAuthUseCase,
    private val findPasswordUseCase: FindPasswordUseCase
): ViewModel() {

    var job: Job? = null

    private val _email = MutableStateFlow("")
    val email get() = _email.asStateFlow()

    private val _nickname = MutableStateFlow("")
    val nickname get() = _nickname.asStateFlow()

    private val _isEmailChecked = MutableStateFlow(false)
    val isEmailChecked get() = _isEmailChecked.asStateFlow()

    private val _isEmailAuthChecked  = MutableStateFlow(false)
    val isEmailAuthChecked  get() = _isEmailAuthChecked .asStateFlow()

    private val _isNicknameChecked = MutableStateFlow(false)
    val isNicknameChecked get() = _isNicknameChecked.asStateFlow()

    // viewModel에서 Toast 메시지 띄우기 위한 Event
    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = _message

    // 서버에서 받은 인증번호
    private val _emailAuthNumber = MutableStateFlow("")
    val emailAuthNumber get() = _emailAuthNumber.asStateFlow()

    // 사용자에게 입력받은 인증번호
    private val _emailAuthNumberCheck = MutableStateFlow("")
    val emailAuthNumberCheck get() = _emailAuthNumberCheck.asStateFlow()

    private val _errorMsgEvent = SingleLiveEvent<String>()
    val errorMsgEvent get() = _errorMsgEvent

    private val _failMsgEvent = SingleLiveEvent<String>()
    val failMsgEvent get() = _failMsgEvent

    private val _joinMsgEvent = SingleLiveEvent<String>()
    val joinMsgEvent get() = _joinMsgEvent

    private val _fcmEvent = SingleLiveEvent<String>()
    val fcmEvent : LiveData<String> get() = _fcmEvent


    // 닉네임 중복 검사
     fun checkNickname(textInputLayout: TextInputLayout){
         viewModelScope.launch(Dispatchers.IO) {
             checkNicknameUseCase.execute(nickname.value!!).collectLatest {
                if(it is ResultType.Success){
                    // 사용가능한 닉네임일 경우
                    _isNicknameChecked.value = true
                }
             }
         }
     }
    // 이메일 인증번호 전송
    fun requestEmailAuthNumber(textInputLayout: TextInputLayout){

        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        // 이메일 형식이 맞을 경우
        if(!email.value.isNullOrBlank() && pattern.matcher(email.value).matches()) {

            // 이메일 중복검사
            checkEmail(email.value!!, textInputLayout)

            // 중복검사 통과시
            if(isEmailChecked.value){
                // 서버로 인증 번호 전송 요청
                viewModelScope.launch(Dispatchers.IO) {
                    requestEmailUseCase.execute(email.value!!).collectLatest {
                        if(it is ResultType.Success){
                            // 인증 번호 저장
                            _emailAuthNumberCheck.value = it.data.data
                        }
                    }
                }
                true
            }else{
                // 중복검사 실패시
                makeToast("중복된 이메일입니다")
                makeTextInputLayoutError(textInputLayout, "중복된 이메일입니다")
                false
            }
        }else{
            makeTextInputLayoutError(textInputLayout, "이메일 형식이 올바르지 않습니다")
            makeToast("이메일 형식이 올바르지 않습니다")
            false
        }
    }
    // 이메일 인증번호 확인
    fun checkEmailAuthNumber(textInputLayout: TextInputLayout){
        // 입력된 인증번호와 받은 인증번호가 일치한지 확인
        if(emailAuthNumberCheck == emailAuthNumber){
            _isEmailAuthChecked.value = true
        }
    }

    // 이메일 중복 검사
    private fun checkEmail(userEmail: String, textInputLayout: TextInputLayout){
        viewModelScope.launch(Dispatchers.IO) {
             checkEmailUseCase.execute(userEmail).collectLatest {
                if(it is ResultType.Success){
                    if(it.data.data.equals(true)){
                        _isEmailChecked.value = true
                    }else{
                        Log.d(TAG, "${it.data}")
                        makeTextInputLayoutError(textInputLayout, "중복된 이메일입니다")
                        makeToast("중복된 이메일입니다")
                        _isEmailChecked.value = false
                    }

                }
            }
        }
    }


    // 비밀번호 찾기

    // 일반 회원기입
    fun signUpUser(token: String, userDto: UserDto){
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase.execute(token, userDto).collectLatest {
                if(it is ResultType.Success) {

                }
            }
        }
    }

    // 일반 로그인
    fun loginUser(map: HashMap<String, String>){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.execute(map).collectLatest {
            }
        }
    }

    fun makeToast(msg: String) {
        _message.value = Event(msg)
    }

    fun makeTextInputLayoutError(textInputLayout: TextInputLayout, msg: String) {
        textInputLayout.error = msg
        textInputLayout.isErrorEnabled = true
    }
}