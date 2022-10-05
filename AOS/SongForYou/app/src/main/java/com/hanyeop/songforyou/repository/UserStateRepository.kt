package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.UserStateRemoteDataSource
import com.hanyeop.songforyou.model.response.UserResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStateRepository @Inject constructor(
    private val userStateRemoteDataSource: UserStateRemoteDataSource
){
    fun getUserInfo(): Flow<ResultType<BaseResponse<UserResponse>>> = flow{
        emit(ResultType.Loading)
        userStateRemoteDataSource.getUserInfo().collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}