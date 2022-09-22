package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.datasource.KakaoRemoteDataSource
import com.hanyeop.songforyou.model.response.ResultSearchKeyword
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KakaoRepository @Inject constructor(
    private val kakaoRemoteDataSource: KakaoRemoteDataSource
) {
    fun getSearchKeyword(query: String): Flow<ResultType<ResultSearchKeyword>> = flow {
        emit(ResultType.Loading)
        kakaoRemoteDataSource.getSearchKeyword(query).collect {
            if(it.meta.total_count == 0){
                emit(ResultType.Empty)
            }else{
                emit(ResultType.Success(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}