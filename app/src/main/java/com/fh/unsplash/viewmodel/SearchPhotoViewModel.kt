package com.fh.unsplash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fh.unsplash.data.repository.SearchPhotoRepository
import com.fh.unsplash.utils.Constants.DEBOUNCE_MILLIS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class SearchPhotoViewModel @ViewModelInject constructor(repository: SearchPhotoRepository) :
    ViewModel() {

    private val query = MutableStateFlow("animals")

    @ExperimentalCoroutinesApi
    @FlowPreview
    val photos = query
        .debounce(DEBOUNCE_MILLIS)
        .filter {
            it.isNotEmpty()
        }
        .flatMapLatest {
            repository.search(it)
        }

    fun searchPhoto(text:String){
        query.value=text
    }
}