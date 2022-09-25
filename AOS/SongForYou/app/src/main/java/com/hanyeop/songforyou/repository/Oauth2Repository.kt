package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.datasource.Oauth2RemoteDataSource
import com.hanyeop.songforyou.model.response.OauthResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Oauth2Repository @Inject constructor(
    private val oauth2RemoteDataSource: Oauth2RemoteDataSource
){
    fun googleLogin(code: String): Flow<ResultType<OauthResponse>> = flow {
        emit(ResultType.Loading)
        oauth2RemoteDataSource.googleLogin(code).collect {
            emit(ResultType.Success(it))
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}