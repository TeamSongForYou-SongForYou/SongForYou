package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.UserStateApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStateRemoteDataSource @Inject constructor(
    private val userStateApi: UserStateApi
){
    fun getUserInfo(): Flow<BaseResponse<UserResponse>> = flow {
        emit(userStateApi.getUserInfo())
    }
}