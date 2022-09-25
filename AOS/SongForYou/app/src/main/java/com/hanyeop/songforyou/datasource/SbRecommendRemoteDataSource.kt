package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.SbRecommendApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecommendResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SbRecommendRemoteDataSource @Inject constructor(
    private val sbRecommendApi: SbRecommendApi
){
    fun getSbRecommend(genre: String, age: Int, gender: String, weather: Int): Flow<BaseResponse<List<RecommendResponse>>> = flow{
        emit(sbRecommendApi.getSbRecommend(genre, age, gender, weather))
    }

    fun getSbRecommendRandom(): Flow<BaseResponse<List<RecommendResponse>>> = flow{
        emit(sbRecommendApi.getSbRecommendRandom())
    }
}

