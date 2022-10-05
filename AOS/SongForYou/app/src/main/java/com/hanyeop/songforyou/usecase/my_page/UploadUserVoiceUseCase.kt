package com.hanyeop.songforyou.usecase.my_page

import com.hanyeop.songforyou.repository.MyPageRepository
import com.hanyeop.songforyou.repository.SongBoxRepository
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadUserVoiceUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository
) {
    fun execute(userSeq: Int, recordFile: MultipartBody.Part) = myPageRepository.uploadUserVoice(userSeq, recordFile)
}