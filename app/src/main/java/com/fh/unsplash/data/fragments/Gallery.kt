package com.fh.unsplash.data.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.fh.unsplash.R
import com.fh.unsplash.callbacks.DetailListener
import com.fh.unsplash.data.adapter.PhotoAdapter
import com.fh.unsplash.data.adapter.PhotoLoadStateAdapter
import com.fh.unsplash.databinding.FragmentGalleryBinding
import com.fh.unsplash.preferences.MainPreferences
import com.fh.unsplash.viewmodel.LatestPhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class Gallery : Fragment(R.layout.fragment_gallery),DetailListener {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel by viewModels<LatestPhotoViewModel>()
    private lateinit var photoAdapter: PhotoAdapter

    @Inject
    lateinit var mainPreference:MainPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)

        setUpRecyclerView()
        fetchLatestPhotos()
        setLoadState()
        navigateToSearch()
        refresh()
    }

    private fun setUpRecyclerView() {
        photoAdapter = PhotoAdapter(this)
        binding.recyclerView.apply {
            adapter = photoAdapter.withLoadStateHeaderAndFooter(
                footer = PhotoLoadStateAdapter{photoAdapter.retry()},
                header = PhotoLoadStateAdapter{photoAdapter.retry()}
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun fetchLatestPhotos(){
        lifecycleScope.launchWhenStarted {
                viewModel.latestPhotos.collect { photos ->
                    photoAdapter.submitData(photos)
                }
        }
    }

    private fun setLoadState(){
        photoAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible=loadState.source.refresh is LoadState.Loading
        }
    }

    private fun navigateToSearch(){
//        binding.searchView.setOnClickListener {
//            findNavController().navigate(R.id.search)
//        }
    }

    private fun refresh(){
        binding.refresh.setOnRefreshListener {
            fetchLatestPhotos()
           binding.refresh.isRefreshing=false
        }
    }

    override fun onPhotoClick(position: Int) {
        val images= photoAdapter.snapshot().items.toTypedArray()
        val action=GalleryDirections.actionGalleryToDetail(position,images)
        findNavController().navigate(action)
    }
}