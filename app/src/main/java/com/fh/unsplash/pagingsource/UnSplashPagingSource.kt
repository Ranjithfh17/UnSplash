package com.fh.unsplash.pagingsource

import androidx.paging.PagingSource
import com.fh.unsplash.api.UnSplashApi
import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.utils.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class UnSplashPagingSource(
    private val unSplashApi: UnSplashApi,
) : PagingSource<Int, LatestPhotoItems>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LatestPhotoItems> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = unSplashApi.fetchLatestPhotos(position, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}