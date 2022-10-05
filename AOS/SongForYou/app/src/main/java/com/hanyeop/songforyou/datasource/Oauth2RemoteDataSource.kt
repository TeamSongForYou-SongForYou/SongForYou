package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.Oauth2Api
import com.hanyeop.songforyou.model.response.OauthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Oauth2RemoteDataSource @Inject constructor(
    private val oauth2Api: Oauth2Api
){
    fun googleLogin(code: String): Flow<OauthResponse> = flow {
        emit(oauth2Api.googleLogin(code))
    }
}