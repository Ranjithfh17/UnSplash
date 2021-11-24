package com.fh.unsplash.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.databinding.AdapterDetailBinding


class DetailAdapter : RecyclerView.Adapter< DetailAdapter.DetailViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding=AdapterDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val value=differ.currentList[position]

        if(value != null){
            holder.bind(value)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class DetailViewHolder(private val binding: AdapterDetailBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(latestPhotoItems: LatestPhotoItems){
            binding.apply {
                Glide.with(itemView)
                    .load(latestPhotoItems.urls.regular)
                    .into(imageView)
            }
        }

    }

    private val differCallback=object :DiffUtil.ItemCallback<LatestPhotoItems>(){
        override fun areItemsTheSame(oldItem: LatestPhotoItems, newItem: LatestPhotoItems) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LatestPhotoItems, newItem: LatestPhotoItems) =
            oldItem == newItem
    }

    val differ=AsyncListDiffer(this,differCallback)

    }





