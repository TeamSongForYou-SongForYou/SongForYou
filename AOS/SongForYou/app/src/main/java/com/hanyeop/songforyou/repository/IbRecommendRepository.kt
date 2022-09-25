package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.IbRecommendRemoteDataSource
import com.hanyeop.songforyou.model.response.RecommendResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IbRecommendRepository @Inject constructor(
    private val ibRecommendRemoteDataSource: IbRecommendRemoteDataSource
) {
    fun getIbRecommendMyRecord(dateLimit: Int): Flow<ResultType<BaseResponse<RecommendResponse>>> = flow{
        emit(ResultType.Loading)
        ibRecommendRemoteDataSource.getIbRecommendMyRecord(dateLimit).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else{

            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}