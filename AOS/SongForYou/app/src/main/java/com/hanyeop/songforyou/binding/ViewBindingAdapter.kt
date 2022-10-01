package com.hanyeop.songforyou.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.DecimalFormat

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

    @BindingAdapter("reviewImage")
    @JvmStatic
    fun ImageView.setreviewImage (imageUrl: String){
        Glide.with(this.context)
            .load(imageUrl)
            .override(300 * 2,300 * 2)
            .into(this)
    }

    @BindingAdapter("youtubeView")
    @JvmStatic
    fun TextView.setYoutubeView(view: Int){
        val dec = DecimalFormat("#,###")
        this.text = dec.format(view)
    }
}