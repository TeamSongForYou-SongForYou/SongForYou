package com.hanyeop.songforyou.view.main.play_list.tab.saved

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

class SavedAdapter (): ListAdapter<MyListResponse, SavedAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemRecommendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(myList: MyListResponse) {
            binding.song = myList.songSeq
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
        val diffUtil = object : DiffUtil.ItemCallback<MyListResponse>() {
            override fun areItemsTheSame(oldItem: MyListResponse, newItem: MyListResponse): Boolean {
                return oldItem.myListSeq == newItem.myListSeq
            }

            override fun areContentsTheSame(oldItem: MyListResponse, newItem: MyListResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}