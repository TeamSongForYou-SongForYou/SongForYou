package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.UbRecommendApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UbRecommendRemoteDataSource @Inject constructor(
    private val ubRecommendApi: UbRecommendApi
){
    fun getUbRecommendMySound(): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(ubRecommendApi.getUbRecommendMySound())
    }
}

