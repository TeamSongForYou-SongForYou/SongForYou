package com.hanyeop.songforyou.model.response

import com.hanyeop.songforyou.model.dto.FileDto
import java.io.Serializable

data class RecordResponse(
    val myRecordSeq: Int,
    val songDto: SongResponse,
    val fileDto: FileDto,
    val myRecordRegTime: String
): Serializable
