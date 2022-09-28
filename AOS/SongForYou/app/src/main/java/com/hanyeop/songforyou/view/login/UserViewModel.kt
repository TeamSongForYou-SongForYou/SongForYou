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
import com.hanyeop.songforyou.utils.*
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
    private val findPasswordUseCase: FindPasswordUseCase,
    private val sharedPreferences: SharedPreferences,
): ViewModel() {

    var job: Job? = null

    val email =  MutableLiveData<String>()

    val nickname = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val passwordCheck = MutableLiveData<String>()

    val gender = MutableLiveData<String>()

    val year = MutableLiveData<String>()



    private val _isPasswordChecked = MutableStateFlow("")
    val isPasswordChecked get() = _isPasswordChecked.asStateFlow()

    private val _isEmailChecked = MutableStateFlow(false)
    val isEmailChecked get() = _isEmailChecked.asStateFlow()

    private val _isEmailAuthChecked  = MutableStateFlow(false)
    val isEmailAuthChecked  get() = _isEmailAuthChecked .asStateFlow()

    private val _isNicknameChecked = MutableStateFlow(false)
    val isNicknameChecked get() = _isNicknameChecked.asStateFlow()

    private val _isJoinChecked = MutableStateFlow(false)
    val isJoinChecked get() = _isJoinChecked.asStateFlow()

    private val _isLoginChecked = MutableStateFlow(false)
    val isLoginChecked get() = _isLoginChecked.asStateFlow()

    val loginEmail = MutableLiveData<String>()

    val loginPassword = MutableLiveData<String>()


    private val _token = MutableStateFlow("")
    val token get() = _token.asStateFlow()

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
//            // 이메일 중복검사
//            checkEmail(email.value!!, textInputLayout)
        Log.d(TAG, isEmailChecked.value.toString())

        // 중복검사 통과시
        if(isEmailChecked.value){
            // 서버로 인증 번호 전송 요청
            viewModelScope.launch(Dispatchers.IO) {
                requestEmailUseCase.execute(email.value!!).collectLatest {
                    if(it is ResultType.Success){
                        // 인증 번호 저장
                        _emailAuthNumberCheck.value = it.data.data
                        makeToast("인증이 완료되었습니다")
                        Log.d(TAG, _emailAuthNumberCheck.value)
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
    }

    // 이메일 인증번호 확인
    fun checkEmailAuthNumber(textInputLayout: TextInputLayout){

        // 입력된 인증번호와 받은 인증번호가 일치한지 확인
        if(emailAuthNumberCheck == emailAuthNumber){
            _isEmailAuthChecked.value = true
        }
    }

    // 이메일 중복 검사
    fun checkEmail(textInputLayout: TextInputLayout){

        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        // 이메일 형식이 맞을 경우
        if(!email.value.isNullOrBlank() && pattern.matcher(email.value).matches()) {
            viewModelScope.launch(Dispatchers.IO) {
                checkEmailUseCase.execute(email.value!!).collectLatest {
                    if(it is ResultType.Success){
                        Log.d(TAG, it.data.data)
                        if(it.data.data.toBoolean()){
                            Log.d(TAG, "true")
                            _isEmailChecked.value = true
                        }else{
                            Log.d(TAG, "${it.data}......")
                            makeTextInputLayoutError(textInputLayout, "중복된 이메일입니다")
                            makeToast("중복된 이메일입니다")
                            _isEmailChecked.value = false
                        }

                    }
                }
            }
        }else{
            makeTextInputLayoutError(textInputLayout, "이메일 형식이 올바르지 않습니다")
            makeToast("이메일 형식이 올바르지 않습니다")
            false
        }
    }

    // 비밀번호 유효성 검사 실행
    fun validatePassword(tilPw: TextInputLayout, tilPwCheck: TextInputLayout): Boolean {

        // 비밀번호를 입력했는지 검사
        if (password.value.isNullOrBlank()) {
            makeTextInputLayoutError(tilPw, "비밀번호를 입력해주세요")
            makeToast("비밀번호를 입력해주세요")
            return false
        }

        // 비밀번호 형식이 틀린 경우
        else {
            val pwRegex = """^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S+$).*$"""
            val pwPattern = Pattern.compile(pwRegex)
            if (!pwPattern.matcher(password.value).matches()) {
                makeTextInputLayoutError(tilPw, "비밀번호를 규칙을 만족하지 못합니다")
                makeToast("비밀번호를 규칙을 만족하지 못합니다")
                return false
            }
        }

        // 비밀번호 재확인 입력했는지 검사
        if (passwordCheck.value.isNullOrBlank()) {
            makeTextInputLayoutError(tilPwCheck, "비밀번호를 입력해주세요")
            makeToast("비밀번호를 입력해주세요")
            return false
        }
        // 두 비밀번호가 일치하지 않는 경우
        if (password.value != passwordCheck.value) {
            makeTextInputLayoutError(tilPwCheck, "비밀번호가 일치하지 않습니다")
            makeToast("비밀번호가 일치하지 않습니다")
            return false
        }

        // 유효성 검사를 다 통과한 경우
        return true
    }
    // 비밀번호 찾기


    // 출생년도 선택시
    fun yearChanged(selected: String){
        year.value = selected
    }
    // 출생년도 선택시
    fun genderChanged(selected: String){
        gender.value = selected
    }
    // 일반 회원가입
    fun signUpUser(){
        val user = UserDto(year.value!!.toInt(), email.value!!, gender.value!!, email.value!!,nickname.value!!, password.value!!)
        Log.d(TAG, user.toString())
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase.execute(user).collectLatest {
                Log.d(TAG,"signUpUser"+it.toString())
                if(it is ResultType.Success) {
                    _joinMsgEvent.postValue(it.data.msg)
                    _isJoinChecked.value = true
                    Log.d(TAG, "signUpUser_joinMsgEvent"+_joinMsgEvent.toString())
                    Log.d(TAG, "signUpUser_isJoinChecked"+_isJoinChecked.toString())
                }
            }
        }
    }

    // 일반 로그인
    fun loginUser(map: HashMap<String, String>){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.execute(map).collectLatest {
                map["id"] = loginEmail.value!!
                map["pass"] = loginPassword.value!!
                // 서버로 로그인 요청
                if(it is ResultType.Success) {
                    _isLoginChecked.value = true
                    // 토큰값 반환
                    Log.d(TAG, "TOKEN : "+  it.data.data.accessToken)
                    _token.value = it.data.data.accessToken
                    Log.d(TAG, "TOKEN : "+  _token.value)

                    sharedPreferences.edit().putString(JWT, "Bearer ${it.data.data.accessToken}").apply()
                }else{
                    Log.d(TAG, "${it}")
                    makeToast("아이디, 비밀번호를 확인해주세요")
                    null
                }
            }
        }
    }

    fun makeToast(msg: String) {
        _message.postValue(Event(msg))
    }

    fun makeTextInputLayoutError(textInputLayout: TextInputLayout, msg: String) {
        textInputLayout.error = msg
        textInputLayout.isErrorEnabled = true
    }
}