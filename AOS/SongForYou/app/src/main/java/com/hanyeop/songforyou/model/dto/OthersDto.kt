package com.hanyeop.songforyou.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OthersDto(
    val name: String,
    val num: Int,
    val type: String,
): Parcelable
