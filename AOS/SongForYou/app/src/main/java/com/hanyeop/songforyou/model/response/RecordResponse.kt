package com.hanyeop.songforyou.model.response

import com.hanyeop.songforyou.model.dto.FileDto

data class RecordResponse(
    val myRecordSeq: Int,
    val songDto: SongResponse,
    val fileDto: FileDto,
    val myRecordRegTime: String
)
