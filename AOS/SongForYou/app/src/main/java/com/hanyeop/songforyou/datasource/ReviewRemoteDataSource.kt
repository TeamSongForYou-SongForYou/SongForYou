package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.ReviewApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.ReviewResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRemoteDataSource @Inject constructor(
    private val reviewApi: ReviewApi
){
    fun getReview(name: String, address: String): Flow<BaseResponse<List<ReviewResponse>>> = flow {
        emit(reviewApi.getReview(name, address))
    }

    fun uploadReview(review: RequestBody, imgFile: MultipartBody.Part): Flow<BaseResponse<ReviewResponse>> = flow {
        emit(reviewApi.uploadReview(review, imgFile))
    }
}