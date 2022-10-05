package com.hanyeop.songforyou.view.main.play_list.tab.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.songforyou.databinding.ItemRecordListBinding
import com.hanyeop.songforyou.model.response.RecordResponse

class RecordAdapter (private val listener: RecordListener): ListAdapter<RecordResponse, RecordAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemRecordListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(record: RecordResponse) {
            binding.record = record
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecordResponse>() {
            override fun areItemsTheSame(oldItem: RecordResponse, newItem: RecordResponse): Boolean {
                return oldItem.myRecordSeq == newItem.myRecordSeq
            }

            override fun areContentsTheSame(oldItem: RecordResponse, newItem: RecordResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}