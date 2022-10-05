package com.hanyeop.songforyou.usecase.ib_recommend

import com.hanyeop.songforyou.repository.IbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIbRecommendMyListUseCase @Inject constructor(
    private val ibRecommendRepository: IbRecommendRepository
){
    fun execute() = ibRecommendRepository.getIbRecommendMyList()
}