package com.hanyeop.songforyou.view.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanyeop.songforyou.databinding.ItemBannerBinding
import java.security.AccessController.getContext

class BannerListAdapter(context:Context):ListAdapter<List<String>, BannerListAdapter.ViewHolder>(diffUtil){
    private val context = context
    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(list: List<String>){
                binding.tvBannerContents1.text = list[0]
                binding.tvBannerContents2.text = list[1]
                binding.tvBannerContents3.text = list[2]
                val id: Int = context.resources.getIdentifier(list[3], "drawable", context.packageName)
                binding.constraintLayout.setBackgroundResource(id)
            }
        }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<List<String>>() {
            override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
                return oldItem[0] == newItem[0]
            }

            override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}