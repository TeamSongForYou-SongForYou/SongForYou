package com.hanyeop.songforyou.model.dto

data class ReviewDto(
    val karaokeName: String,
    val karaokeAddress: String,
    val reviewPrice: String,
    val reviewPayType: String,
    val reviewEmployee: String,
    val reviewToilet: String,
    val reviewCleanness: Int,
    val reviewSoundQuality: Int,
    val reviewContent: String
)
