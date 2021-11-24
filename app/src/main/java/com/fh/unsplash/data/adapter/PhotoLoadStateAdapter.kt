package com.fh.unsplash.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fh.unsplash.databinding.AdapterLoadStateBinding

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.PhotoLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadStateViewHolder {
        val binding =
            AdapterLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoLoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)

    }

    inner class PhotoLoadStateViewHolder(private val binding: AdapterLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                noResult.isVisible = loadState !is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
            }

        }


    }


}