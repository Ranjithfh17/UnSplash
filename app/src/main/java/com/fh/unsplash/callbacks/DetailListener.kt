package com.fh.unsplash.callbacks

import com.fh.unsplash.data.model.LatestPhotoItems

interface DetailListener {
    fun onPhotoClick(position:Int)
}