package com.hanyeop.songforyou.repository

import android.util.Log
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.UbRecommendRemoteDataSource
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UbRecommendRepository @Inject constructor(
    private val ubRecommendRemoteDataSource: UbRecommendRemoteDataSource
) {

    fun getUbRecommendMySound(): Flow<ResultType<BaseResponse<List<SongResponse>>>> = flow{
        emit(ResultType.Loading)
        ubRecommendRemoteDataSource.getUbRecommendMySound().collect{
            Log.d("test5", "-------------getUbRecommendList:$it")
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