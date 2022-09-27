package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.UserApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {
    // 일반 회원가입
    fun signUpUser(
        userDto: UserDto
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.signUpUser(userDto))
    }

    // 일반 로그인
    fun loginUser(
        map: HashMap<String, String>
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.loginUser(map))
    }

    // 이메일 중복 검사
    fun checkEmail(
        userEmail: String
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.checkEmail(userEmail))
    }

    // 닉네임 중복 검사
    fun checkNickname(
        userNickname: String
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.checkNickname(userNickname))
    }


    // 이메일 인증번호 전송
    fun requestEmailAuth(
        userEmail: String
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.requestEmailAuth(userEmail))
    }

    // 비밀번호 찾기
    fun findPassword(
        map: HashMap<String, String>
    ): Flow<BaseResponse<String>> = flow {
        emit(userApi.findPassword(map))
    }
}