package com.hanyeop.songforyou.model.response

data class ReviewResponse(
    val reviewSeq: Int,
    val reviewPrice: String,
    val reviewPayType: String,
    val reviewEmployee: String,
    val reviewToilet: String,
    val reviewCleanness: Int,
    val reviewSoundQuality: Int,
    val reviewContent: String,
    val reviewRegTime: String
)
