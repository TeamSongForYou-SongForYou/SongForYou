package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.SongRemoteDataSource
import com.hanyeop.songforyou.model.response.LyricsResponse
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
    private val songRemoteDataSource: SongRemoteDataSource
){
    fun getSongDetail(songSeq: Int): Flow<ResultType<BaseResponse<SongResponse>>> = flow{
        emit(ResultType.Loading)
        songRemoteDataSource.getSongDetail(songSeq).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun getLyrics(songSeq: Int): Flow<ResultType<BaseResponse<LyricsResponse>>> = flow{
        emit(ResultType.Loading)
        songRemoteDataSource.getLyrics(songSeq).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun songDisLike(songSeq: Int): Flow<ResultType<BaseResponse<String>>> = flow{
        emit(ResultType.Loading)
        songRemoteDataSource.songDisLike(songSeq).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun songSearch(songName: String): Flow<ResultType<BaseResponse<List<SongResponse>>>> = flow{
        emit(ResultType.Loading)
        songRemoteDataSource.songSearch(songName).collect{
            if(it.success){
                emit(ResultType.Success(it))
            }else if(!it.success){
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

}