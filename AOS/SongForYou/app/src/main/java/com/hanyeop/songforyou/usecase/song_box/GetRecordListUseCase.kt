package com.hanyeop.songforyou.usecase.song_box

import com.hanyeop.songforyou.repository.SongBoxRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecordListUseCase @Inject constructor(
    private val songBoxRepository: SongBoxRepository
) {
    fun execute() = songBoxRepository.getRecordList()
}