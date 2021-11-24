package com.fh.unsplash.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fh.unsplash.api.UnSplashApi
import com.fh.unsplash.data.model.SearchPhotoItems
import com.fh.unsplash.pagingsource.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPhotoRepository @Inject constructor(private val unSplashApi: UnSplashApi) {

    fun search(query: String): Flow<PagingData<SearchPhotoItems>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {SearchPagingSource(unSplashApi, query)}
    ).flow


}