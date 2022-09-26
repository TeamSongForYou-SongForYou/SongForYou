package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.UserRemoteDataSource
import com.hanyeop.songforyou.model.dto.UserDto
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
    fun signUpUser(token: String, userDto: UserDto) = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.signUpUser(token, userDto).collect {
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

    // 일반 로그인
    fun loginUser(map: HashMap<String, String>): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        userRemoteDataSource.loginUser(map).collect {
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
}