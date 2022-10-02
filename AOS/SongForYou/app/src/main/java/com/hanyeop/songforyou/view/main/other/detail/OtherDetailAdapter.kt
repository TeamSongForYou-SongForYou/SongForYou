package com.hanyeop.songforyou.view.main.other.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.songforyou.databinding.ItemRecommendListBinding
import com.hanyeop.songforyou.databinding.ItemRecordListBinding
import com.hanyeop.songforyou.model.response.MyListResponse
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.view.main.home.SongDetailListener

class OtherDetailAdapter (private val listener: SongDetailListener): ListAdapter<SongResponse, OtherDetailAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemRecommendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
            }
            binding.btnRecord.setOnClickListener{
                listener.onRecordClick(getItem(adapterPosition))
            }
        }

        fun bind(song: SongResponse) {
            binding.song = song
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecommendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SongResponse>() {
            override fun areItemsTheSame(oldItem: SongResponse, newItem: SongResponse): Boolean {
                return oldItem.SongSeq == newItem.SongSeq
            }

            override fun areContentsTheSame(oldItem: SongResponse, newItem: SongResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}