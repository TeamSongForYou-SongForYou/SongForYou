package com.hanyeop.songforyou.model.dto

import java.io.Serializable

data class FileDto(
    val fileSeq: Int,
    val fileOriginalName: String,
    val fileSavedName: String,
    val fileSavedPath: String,
    val fileType: String,
    val fileRegTime: String
): Serializable
