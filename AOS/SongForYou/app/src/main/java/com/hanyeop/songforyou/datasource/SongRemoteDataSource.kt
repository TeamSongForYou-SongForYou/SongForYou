package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.SongApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.LyricsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRemoteDataSource @Inject constructor(
    private val songApi: SongApi
) {
    fun getSongDetail(songSeq: Int): Flow<BaseResponse<SongDetailResponse>> = flow {
        emit(songApi.getSongDetail(songSeq))
    }

    fun getLyrics(songSeq: Int): Flow<BaseResponse<LyricsResponse>> = flow {
        emit(songApi.getLyrics(songSeq))
    }
}