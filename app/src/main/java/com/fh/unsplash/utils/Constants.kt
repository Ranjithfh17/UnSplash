package com.fh.unsplash.utils

import com.fh.unsplash.BuildConfig

object Constants {
    const val BASE_URL = "https://api.unsplash.com/"

//    const val BASE_URL = "https://api.unsplash.com/photos"
    const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    const val STARTING_PAGE_INDEX=1
    const val DEBOUNCE_MILLIS=500L

}