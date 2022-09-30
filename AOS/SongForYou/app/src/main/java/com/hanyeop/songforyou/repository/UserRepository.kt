package com.hanyeop.songforyou.repository

import android.util.Log
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.UserRemoteDataSource
import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.model.response.OauthResponse
import com.hanyeop.songforyou.model.response.TokenResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {

    // 일반 회원가입
    fun signUpUser(userDto: UserDto) = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.signUpUser(userDto).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    private val TAG = "test5"
    // 일반 로그인
    fun loginUser(map: HashMap<String, String>): Flow<ResultType<BaseResponse<TokenResponse>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.loginUser(map).collect {
            Log.d(TAG, "loginUser: $it")
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }

    }.catch { e ->
        emit(ResultType.Error(e))
        Log.d(TAG, "loginUser: $e")
    }

    // 이메일 중복 검사
    fun checkEmail(userEmail: String): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.checkEmail(userEmail).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }


    // 닉네임 중복 검사
    fun checkNickname(userNickname: String): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.checkNickname(userNickname).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }


    // 이메일 인증번호 전송
    fun requestEmailAuth(userEmail: String): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.requestEmailAuth(userEmail).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    // 비밀번호 찾기
    fun findPassword(map: HashMap<String, String>): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.findPassword(map).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun googleLogin(code: String): Flow<ResultType<OauthResponse>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.googleLogin(code).collect {
            emit(ResultType.Success(it))
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}