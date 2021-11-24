package com.fh.unsplash.pagingsource

import androidx.paging.PagingSource
import com.fh.unsplash.api.UnSplashApi
import com.fh.unsplash.data.model.SearchPhotoItems
import com.fh.unsplash.data.model.SearchPhotos
import com.fh.unsplash.utils.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val unSplashApi: UnSplashApi,
    private val query: String
) : PagingSource<Int,SearchPhotoItems>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchPhotoItems> {
        val position=params.key ?: STARTING_PAGE_INDEX

        return try{
            val response=unSplashApi.search(query,position,params.loadSize)
            val photos=response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )


        }catch (exception:IOException){
            LoadResult.Error(exception)
        }catch (exception:HttpException){
            LoadResult.Error(exception)
        }
    }
}