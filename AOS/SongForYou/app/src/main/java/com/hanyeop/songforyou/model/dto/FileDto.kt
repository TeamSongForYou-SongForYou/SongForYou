package com.hanyeop.songforyou.model.dto

data class FileDto(
    val fileSeq: Int,
    val fileOriginalName: String,
    val fileSavedName: String,
    val fileSavedPath: String,
    val fileType: String,
    val fileRegTime: String
)
