package com.hanyeop.songforyou.view.karaoke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.songforyou.databinding.ItemReviewBinding
import com.hanyeop.songforyou.model.response.ReviewResponse

class ReviewAdapter(): ListAdapter<ReviewResponse, ReviewAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(review: ReviewResponse){
            binding.apply {
                tvTime.text = review.reviewRegTime
                ratingCleanness.rating = review.reviewCleanness.toFloat()
                ratingSoundQuality.rating = review.reviewSoundQuality.toFloat()
                tvComment.text = review.reviewContent
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ReviewResponse>(){
            override fun areItemsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse): Boolean {
                return oldItem.reviewSeq == newItem.reviewSeq
            }

            override fun areContentsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}