package com.fh.unsplash.data.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.fh.unsplash.R
import com.fh.unsplash.data.adapter.PhotoAdapter
import com.fh.unsplash.data.adapter.PhotoLoadStateAdapter
import com.fh.unsplash.data.adapter.SearchPhotoAdapter
import com.fh.unsplash.databinding.FragmentSearchBinding
import com.fh.unsplash.viewmodel.SearchPhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class Search : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var photoAdapter: SearchPhotoAdapter
    private val viewModel by viewModels<SearchPhotoViewModel>()


    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)


        searchPhoto()
        setUpRecyclerView()
        setLoadState()
        getSearchPhotos()




    }


    private fun setUpRecyclerView() {
        photoAdapter = SearchPhotoAdapter()
        binding.recyclerView.apply {
            adapter = photoAdapter.withLoadStateHeaderAndFooter(
                footer = PhotoLoadStateAdapter { photoAdapter.retry() },
                header = PhotoLoadStateAdapter { photoAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun searchPhoto(){
        binding.apply {
            searchView.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty()){
                        viewModel.searchPhoto(s.toString())
                        getSearchPhotos()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun getSearchPhotos(){
        lifecycleScope.launchWhenStarted {
            viewModel.photos.collect {
                photoAdapter.submitData(it)
            }
        }
    }


    private fun setLoadState() {
        photoAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

        }
    }
}