package com.fh.unsplash.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fh.unsplash.callbacks.DetailListener
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.databinding.AdapterPhotosBinding

class PhotoAdapter(private val listener: DetailListener) :
    PagingDataAdapter<LatestPhotoItems, PhotoAdapter.PhotoViewHolder>(DIFFER) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            AdapterPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val latestPhotoItems = getItem(position)

        if (latestPhotoItems != null) {
            holder.bind(latestPhotoItems)
        }

    }


    inner class PhotoViewHolder(private val binding: AdapterPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(latestPhotoItems: LatestPhotoItems) {
            binding.apply {
                Glide.with(itemView)
                    .load(latestPhotoItems.urls.regular)
                    .into(imageView)
                name.text = latestPhotoItems.user.name
            }
        }

        init {
            binding.root.setOnClickListener {
                val photos=bindingAdapterPosition
                listener.onPhotoClick(photos)
            }

        }
    }



    companion object {
        private val DIFFER = object : DiffUtil.ItemCallback<LatestPhotoItems>() {
            override fun areItemsTheSame(oldItem: LatestPhotoItems, newItem: LatestPhotoItems) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: LatestPhotoItems, newItem: LatestPhotoItems) =
                oldItem == newItem
        }
    }


}








