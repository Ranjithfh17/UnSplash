package com.fh.unsplash.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.volley.toolbox.Volley
import com.fh.unsplash.api.UnSplashApi
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.data.model.LatestPhotos
import com.fh.unsplash.utils.Resource
import com.fh.unsplash.pagingsource.UnSplashPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LatestPhotoRepository @Inject constructor(private val unSplashApi: UnSplashApi) {

    fun fetchLatestPhotos(page: Int, per_page: Int): Flow<Resource<LatestPhotos>> = flow {
        emit(Resource.Loading())
        val photos = unSplashApi.fetchLatestPhotos(page, per_page)

        emit(Resource.Success(photos))

    }.catch { error ->
        emit(Resource.Error(error.toString()))
    }


    fun getList(): Flow<PagingData<LatestPhotoItems>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { UnSplashPagingSource(unSplashApi) }
    ).flow







}