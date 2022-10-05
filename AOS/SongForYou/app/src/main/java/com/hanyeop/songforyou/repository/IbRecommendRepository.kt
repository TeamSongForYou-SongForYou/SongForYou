package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.IbRecommendRemoteDataSource
import com.hanyeop.songforyou.model.response.SongResponse
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

    fun getIbRecommendMyList(): Flow<ResultType<BaseResponse<List<SongResponse>>>> = flow{
        emit(ResultType.Loading)
        ibRecommendRemoteDataSource.getIbRecommendMyList().collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun getIbRecommendMyRecord(dateLimit: Int): Flow<ResultType<BaseResponse<List<SongResponse>>>> = flow{
        emit(ResultType.Loading)
        ibRecommendRemoteDataSource.getIbRecommendMyRecord(dateLimit).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun getIbRecommendBefore(SongSeq: Int): Flow<ResultType<BaseResponse<List<SongResponse>>>> = flow{
        emit(ResultType.Loading)
        ibRecommendRemoteDataSource.getIbRecommendBefore(SongSeq).collect{
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