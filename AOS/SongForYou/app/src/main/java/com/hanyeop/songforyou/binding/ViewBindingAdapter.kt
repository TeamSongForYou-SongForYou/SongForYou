package com.hanyeop.songforyou.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewBindingAdapter {
    @BindingAdapter("summaryImage")
    @JvmStatic
    fun ImageView.setSummaryImage (imageUrl: String){
        Glide.with(this.context)
            .load(imageUrl)
            .override(100 * 2,100 * 2)
            .into(this)
    }

    @BindingAdapter("songImage")
    @JvmStatic
    fun ImageView.setSongImage (imageUrl: String){
        Glide.with(this.context)
            .load(imageUrl)
            .override(140 * 2,140 * 2)
            .into(this)
    }
}