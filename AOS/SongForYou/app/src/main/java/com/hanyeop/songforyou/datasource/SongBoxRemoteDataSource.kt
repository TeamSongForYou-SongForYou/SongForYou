package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.SongBoxApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecordResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongBoxRemoteDataSource @Inject constructor(
    private val songBoxApi: SongBoxApi
){
    fun addSongBox(songSeq: Int): Flow<BaseResponse<String>> = flow {
        emit(songBoxApi.addSongBox(songSeq))
    }

    fun deleteSongBox(songSeq: Int): Flow<BaseResponse<String>> = flow {
        emit(songBoxApi.deleteSongBox(songSeq))
    }

    fun getRecordList(): Flow<BaseResponse<List<RecordResponse>>> = flow {
        emit(songBoxApi.getRecordList())
    }

    fun uploadRecord(songSeq: Int, recordFile: MultipartBody.Part): Flow<BaseResponse<RecordResponse>> = flow {
        emit(songBoxApi.uploadRecord(songSeq, recordFile))
    }
}