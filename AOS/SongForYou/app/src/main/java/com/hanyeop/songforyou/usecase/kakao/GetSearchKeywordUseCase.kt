package com.hanyeop.songforyou.usecase.kakao

import com.hanyeop.songforyou.repository.KakaoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchKeywordUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository
) {
    fun execute(query: String, x: String, y: String) = kakaoRepository.getSearchKeyword(query, x, y)
}