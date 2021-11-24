package com.fh.unsplash.api

import com.fh.unsplash.data.model.LatestPhotoItems
import com.fh.unsplash.data.model.LatestPhotos
import com.fh.unsplash.data.model.SearchPhotos
import com.fh.unsplash.utils.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnSplashApi {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos")
    suspend fun fetchLatestPhotos(
        @Query("page") page:Int,
        @Query("per_page") per_page:Int,
    ):LatestPhotos

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun search(
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ):SearchPhotos
}