package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.IbRecommendApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IbRecommendRemoteDataSource @Inject constructor(
    private val ibRecommendApi: IbRecommendApi
){

    fun getIbRecommendMyList(): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(ibRecommendApi.getIbRecommendMyList())
    }

    fun getIbRecommendMyRecord(dateLimit: Int): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(ibRecommendApi.getIbRecommendMyRecord(dateLimit))
    }

    fun getIbRecommendBefore(SongSeq: Int): Flow<BaseResponse<List<SongResponse>>> = flow{
        emit(ibRecommendApi.getIbRecommendBefore(SongSeq))
    }
}

