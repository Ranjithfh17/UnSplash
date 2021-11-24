package com.fh.unsplash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fh.unsplash.data.repository.LatestPhotoRepository

class LatestPhotoViewModel @ViewModelInject constructor(repository: LatestPhotoRepository) : ViewModel() {
    val latestPhotos= repository.getList()
}




