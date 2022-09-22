package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.KakaoApi
import com.hanyeop.songforyou.model.response.ResultSearchKeyword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KakaoRemoteDataSource @Inject constructor(
    private val kakaoAPI: KakaoApi
) {
    fun getSearchKeyword(query: String, x: String, y: String): Flow<ResultSearchKeyword> = flow {
        emit(kakaoAPI.getSearchKeyword(query = query, x = x, y = y))
    }
}