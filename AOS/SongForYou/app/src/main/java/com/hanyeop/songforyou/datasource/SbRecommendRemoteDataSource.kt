package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.SbRecommendApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SbRecommendRemoteDataSource @Inject constructor(
    private val sbRecommendApi: SbRecommendApi
){
    fun getSbRecommend(genre: String, age: Int, gender: String, weather: Int): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(sbRecommendApi.getSbRecommend(genre, age, gender, weather))
    }

    fun getSbRecommendRandom(): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(sbRecommendApi.getSbRecommendRandom())
    }
}

