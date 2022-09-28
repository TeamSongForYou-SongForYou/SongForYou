package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.ReviewRemoteDataSource
import com.hanyeop.songforyou.model.response.ReviewResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val reviewRemoteDataSource: ReviewRemoteDataSource
) {

    fun getReview(name: String, address: String): Flow<ResultType<BaseResponse<List<ReviewResponse>>>> = flow {
        emit(ResultType.Loading)
        reviewRemoteDataSource.getReview(name, address).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            } else {
                emit(ResultType.Empty)
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}