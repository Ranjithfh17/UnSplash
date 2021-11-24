package com.fh.unsplash.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.data.model.SearchPhotoItems
import com.fh.unsplash.databinding.AdapterPhotosBinding
import com.fh.unsplash.databinding.AdapterSearchPhotosBinding

class SearchPhotoAdapter : PagingDataAdapter<SearchPhotoItems, SearchPhotoAdapter.SearchPhotoViewHolder>(DIFFER) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPhotoViewHolder {
        val binding=AdapterSearchPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPhotoViewHolder, position: Int) {
        val latestPhotoItems=getItem(position)

        if (latestPhotoItems != null) {
            holder.bind(latestPhotoItems)
        }
    }


    class SearchPhotoViewHolder(private val binding: AdapterSearchPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(latestPhotoItems: SearchPhotoItems){
                binding.apply {
                    Glide.with(itemView)
                        .load(latestPhotoItems.urls.regular)
                        .into(imageView)
                }
            }
    }

    companion object {
        private val DIFFER = object : DiffUtil.ItemCallback<SearchPhotoItems>() {
            override fun areItemsTheSame(oldItem: SearchPhotoItems, newItem: SearchPhotoItems) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SearchPhotoItems, newItem: SearchPhotoItems) =
                oldItem == newItem
        }
    }

}