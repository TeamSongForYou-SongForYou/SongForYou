package com.hanyeop.songforyou.usecase.song

import com.hanyeop.songforyou.repository.SongRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongSearchUseCase @Inject constructor(
    private val songRepository: SongRepository
) {
    fun execute(songName: String) = songRepository.songSearch(songName)
}