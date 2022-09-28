package com.hanyeop.songforyou.usecase.review

import com.hanyeop.songforyou.repository.ReviewRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
){
    fun execute(review: RequestBody, imgFile: MultipartBody.Part) = reviewRepository.uploadReview(review, imgFile)
}