package com.hanyeop.songforyou.usecase.song_box

import com.hanyeop.songforyou.repository.SongBoxRepository
import com.hanyeop.songforyou.repository.SongRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteSongBoxUseCase @Inject constructor(
    private val songBoxRepository: SongBoxRepository
) {
    fun execute(songSeq: Int) = songBoxRepository.deleteSongBox(songSeq)
}