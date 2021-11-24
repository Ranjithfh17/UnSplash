package com.fh.unsplash.data.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fh.unsplash.R
import com.fh.unsplash.data.adapter.DetailAdapter
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.databinding.FragmentDetailBinding


class Detail : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailArgs>()
    private lateinit var detailAdapter: DetailAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setViewPager()





    }

    private fun setViewPager() {
        detailAdapter = DetailAdapter()
        binding.viewPager.adapter = detailAdapter


        detailAdapter.differ.submitList(args.photos.toList())
        Log.i("TAG", "setViewPager:${args.photos.toList()} ")

        binding.viewPager.setCurrentItem(args.position, false)

    }





}