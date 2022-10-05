package com.hanyeop.songforyou.usecase.song_box

import com.hanyeop.songforyou.repository.SongBoxRepository
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadRecordUseCase @Inject constructor(
    private val songBoxRepository: SongBoxRepository
) {
    fun execute(songSeq: Int, recordFile: MultipartBody.Part) = songBoxRepository.uploadRecord(songSeq, recordFile)
}