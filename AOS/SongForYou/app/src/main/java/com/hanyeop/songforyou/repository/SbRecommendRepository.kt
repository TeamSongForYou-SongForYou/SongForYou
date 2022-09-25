package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.SbRecommendRemoteDataSource
import com.hanyeop.songforyou.model.response.RecommendResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SbRecommendRepository @Inject constructor(
    private val sbRecommendRemoteDataSource: SbRecommendRemoteDataSource
) {

    fun getSbRecommend(genre: String, age: Int, gender: String, weather: Int): Flow<ResultType<BaseResponse<List<RecommendResponse>>>> = flow{
        emit(ResultType.Loading)
        sbRecommendRemoteDataSource.getSbRecommend(genre, age, gender, weather).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else{

            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun getSbRecommendRandom(): Flow<ResultType<BaseResponse<List<RecommendResponse>>>> = flow{
        emit(ResultType.Loading)
        sbRecommendRemoteDataSource.getSbRecommendRandom().collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else{

            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}