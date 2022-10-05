package com.hanyeop.songforyou.usecase.review

import com.hanyeop.songforyou.repository.ReviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
){
    fun execute(name: String, address: String) = reviewRepository.getReview(name, address)
}